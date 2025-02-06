package org.example.transactionservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.transactionservice.model.Transaction;
import org.example.transactionservice.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/deposit/{accountId}/{amount}")
    public void deposit(@PathVariable Long accountId,@PathVariable double amount) {
        transactionService.deposit(accountId, amount);
    }

    @GetMapping("/withdraw/{accountId}/{amount}")
    public void withdraw(@PathVariable Long accountId, @PathVariable double amount) {
        transactionService.withdraw(accountId, amount);
    }

    @GetMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public void transfer(@PathVariable Long fromAccountId, @PathVariable Long toAccountId, @PathVariable double amount) {
        transactionService.transfer(fromAccountId, toAccountId, amount);
    }

    @GetMapping("/get/{transactionId}")
    public Transaction getTransaction(@PathVariable Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @GetMapping("/get/all")
    public Iterable<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/get/account/{accountId}")
    public Iterable<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
