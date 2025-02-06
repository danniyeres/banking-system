package org.example.transactionservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Deposit extends Transaction {

    private TransactionType type = TransactionType.DEPOSIT;
    private String ownerName;

    public Deposit(TransactionType type, Long accountId, double amount, String ownerName) {
        super(accountId, amount);
        this.type = type;
        this.ownerName = ownerName;
    }

    public Deposit() {
    }
}
