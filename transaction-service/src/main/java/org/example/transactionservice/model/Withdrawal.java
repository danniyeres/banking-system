package org.example.transactionservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Withdrawal extends Transaction {

    private TransactionType type = TransactionType.WITHDRAWAL;
    private String ownerName;

    public Withdrawal(Long id,TransactionType type, Long accountId, Long amount, String ownerName, Date createdAt) {
        super(id, accountId, amount, createdAt);
        this.type = type;
        this.ownerName = ownerName;
    }

    public Withdrawal() {
    }

}
