package org.example.transactionservice.repository;

import org.example.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(Long id);
}
