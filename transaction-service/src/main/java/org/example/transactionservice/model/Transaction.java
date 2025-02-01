package org.example.transactionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long amount;
    private Date createdAt;

    public Transaction(Long id, Long accountId, Long amount, Date createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Transaction() {
    }
}
