package org.example.transactionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.transactionservice.feign.AccountClient;
import org.example.transactionservice.model.Deposit;
import org.example.transactionservice.model.Transaction;
import org.example.transactionservice.model.TransactionType;
import org.example.transactionservice.model.Withdraw;
import org.example.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;

    public void deposit(Long accountId, double amount) {
        var account = accountClient.getAccount(accountId);
        if (account == null)
            throw new RuntimeException("Account not found");

        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        accountClient.deposit(accountId, amount);

        transactionRepository.save(new Deposit(TransactionType.DEPOSIT, accountId, amount, account.getOwnerName()));

        log.info("Deposited {} to account {}", amount, accountId);
    }

    public void withdraw(Long accountId, double amount) {
        var account = accountClient.getAccount(accountId);
        if (account == null)
            throw new RuntimeException("Account not found");

        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        if (account.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient balance");

        accountClient.withdraw(accountId, amount);

        transactionRepository.save(new Withdraw(TransactionType.WITHDRAW, accountId, amount, account.getOwnerName()));

        log.info("Withdrawn {} from account {}", amount, accountId);
    }

    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        if (fromAccountId.equals(toAccountId))
            throw new IllegalArgumentException("Cannot transfer to the same account");

        var fromAccount = accountClient.getAccount(fromAccountId);
        var toAccount = accountClient.getAccount(toAccountId);

        if (fromAccount == null || toAccount == null)
            throw new RuntimeException("Account not found");

        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        if (fromAccount.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient balance");

        accountClient.withdraw(fromAccountId, amount);
        accountClient.deposit(toAccountId, amount);

        log.info("Transferred {} from account {} to account {}", amount, fromAccountId, toAccountId);
    }



    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow();
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findTransactionsByAccountId(accountId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
