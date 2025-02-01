package org.example.transactionservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transfer extends Transaction{

    private Long toAccountId;
    private TransactionType type = TransactionType.TRANSFER;

    public Transfer (){

    }

    public Transfer(Long id, Long accountId, Long amount, Date createdAt, Long toAccountId) {
        super(id, accountId, amount, createdAt);
        this.toAccountId = toAccountId;
    }
}
