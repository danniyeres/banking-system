package org.example.transactionservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Withdraw extends Transaction {

    private TransactionType type = TransactionType.WITHDRAW;
    private String ownerName;

    public Withdraw(TransactionType type, Long accountId, double amount, String ownerName) {
        super(accountId, amount);
        this.type = type;
        this.ownerName = ownerName;
    }

    public Withdraw() {
    }

}
