package org.example.transactionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private double amount;
    private LocalDateTime createdAt;

    public Transaction(Long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Transaction() {
    }
}
