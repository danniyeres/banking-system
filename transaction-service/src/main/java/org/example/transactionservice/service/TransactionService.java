package org.example.transactionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.transactionservice.dto.TransactionNotify;
import org.example.transactionservice.feign.AccountClient;
import org.example.transactionservice.feign.UserClient;
import org.example.transactionservice.kafka.TransactionProducer;
import org.example.transactionservice.model.Transaction;
import org.example.transactionservice.model.TransactionType;
import org.example.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;
    private final UserClient userClient;
    private final TransactionProducer transactionProducer;

    public void deposit(Long accountId, double amount) {
        var account = accountClient.getAccount(accountId);
        if (account == null)
            throw new RuntimeException("Account not found");

        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        accountClient.deposit(accountId, amount);
        var user = userClient.getUserById(account.getOwnerId());
        var transaction = Transaction.builder()
                .toAccountId(accountId)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .ownerName(account.getOwnerName())
                .timestamp(new Date())
                .build();

        transactionRepository.save(transaction);

        var transactionNotify = TransactionNotify.builder()
                .email(user.getEmail())
                .message("Deposited " + amount + " to account " + accountId)
                .build();

        transactionProducer.sendNotification(transactionNotify);

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

        var user = userClient.getUserById(account.getOwnerId());
        var transaction = Transaction.builder()
                .fromAccountId(accountId)
                .amount(amount)
                .type(TransactionType.WITHDRAW)
                .ownerName(account.getOwnerName())
                .timestamp(new Date())
                .build();

        var transactionNotify = TransactionNotify.builder()
                .email(user.getEmail())
                .message("Withdrawn " + amount + " from account " + accountId)
                .build();

        transactionProducer.sendNotification(transactionNotify);
        transactionRepository.save(transaction);
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

        var transaction = Transaction.builder()
                .fromAccountId(fromAccountId)
                .toAccountId(toAccountId)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .ownerName(fromAccount.getOwnerName())
                .timestamp(new Date())
                .build();

        transactionRepository.save(transaction);

        var fromUser = userClient.getUserById(fromAccount.getOwnerId());
        var toUser = userClient.getUserById(toAccount.getOwnerId());

        var fromTransactionNotify = TransactionNotify.builder()
                .email(fromUser.getEmail())
                .message("Transferred " + amount + " to account " + toAccountId)
                .build();

        var toTransactionNotify = TransactionNotify.builder()
                .email(toUser.getEmail())
                .message("Received " + amount + " from account " + fromAccountId)
                .build();

        transactionProducer.sendNotification(fromTransactionNotify);
        transactionProducer.sendNotification(toTransactionNotify);

        log.info("Transferred {} from account {} to account {}", amount, fromAccountId, toAccountId);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
