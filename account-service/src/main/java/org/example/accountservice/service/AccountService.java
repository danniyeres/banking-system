package org.example.accountservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accountservice.feign.UserClient;
import org.example.accountservice.model.Account;
import org.example.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CardService cardService;
    private final UserClient userClient;

    public Account createAccount(Account accountDTO) {
        var account = Account.builder()
                .ownerName(accountDTO.getOwnerName())
                .ownerId(accountDTO.getOwnerId())
                .balance(accountDTO.getBalance())
                .build();
        accountRepository.save(account);
        var card = cardService.createCard(account);
        account.setCard(card);
        accountRepository.save(account);
        log.info("Created account {}",account.getId());
        return account;
    }

    public Account createAccountWithUserId(Long userId) {
        var userDTO = userClient.getUserById(userId);

        if (userDTO == null)
            throw new IllegalArgumentException("User not found with id: " + userId);

        var account = Account.builder()
                .ownerId(userId)
                .ownerName(userDTO.getFirstName() + " " + userDTO.getLastName())
                .balance(0)
                .build();
        accountRepository.save(account);
        var card = cardService.createCard(account);
        account.setCard(card);
        accountRepository.save(account);
        log.info("Created account {}, for user with id: {}",account.getId(), userId);
        return account;

    }
    public Account getAccount(Long accountId) {
        if (accountRepository.findAccountById(accountId) == null)
            throw new IllegalArgumentException("Account not found with id: " + accountId);

        return accountRepository.findAccountById(accountId);
    }

    public Account updateAccount(Long accountId, Account accountDTO) {
        var account = accountRepository.findAccountById(accountId);
        account.setOwnerName(accountDTO.getOwnerName());
        account.setBalance(accountDTO.getBalance());
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long accountId) {
        var account = accountRepository.findAccountById(accountId);
        cardService.deleteCard(accountId);
        accountRepository.delete(account);
        log.info("Deleted account with id: {}", accountId);
    }

    public void deposit(Long accountId, double amount) {
        var account = accountRepository.findAccountById(accountId);
        if (account == null)
            throw new IllegalArgumentException("Account not found with id: " + accountId);

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        log.info("Deposited {} to account with id: {}", amount, accountId);
    }

    public void withdraw(Long accountId, double amount) {
        var account = accountRepository.findAccountById(accountId);
        if (account == null)
            throw new IllegalArgumentException("Account not found with id: " + accountId);
        if (account.getBalance() < amount)
            throw new IllegalArgumentException("Not enough funds for withdrawal.");

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        log.info("Withdrawn {} from account with id: {}", amount, accountId);
    }
}
