package org.example.transactionservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transfer extends Transaction{

    private Long toAccountId;
    private TransactionType type = TransactionType.TRANSFER;

    public Transfer (){

    }

    public Transfer(TransactionType type, Long accountId, double amount, String ownerName) {
        super(accountId, amount);
        this.type = type;
        this.toAccountId = toAccountId;
    }
}
