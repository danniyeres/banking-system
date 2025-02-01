package org.example.transactionservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Deposit extends Transaction {

    private TransactionType type = TransactionType.DEPOSIT;
    private String ownerName;

    public Deposit(Long id,TransactionType type, Long accountId, Long amount, String ownerName, Date createdAt) {
        super(id, accountId, amount, createdAt);
        this.type = type;
        this.ownerName = ownerName;
    }

    public Deposit() {
    }

}
