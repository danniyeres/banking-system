package org.example.transactionservice.model;

public class TransactionFactory {

    public static Transaction createTransaction(TransactionType type) {
        return switch (type) {
            case DEPOSIT -> new Deposit();
            case WITHDRAW -> new Withdraw();
            case TRANSFER -> new Transfer();
            default -> null;
        };
    }
}
