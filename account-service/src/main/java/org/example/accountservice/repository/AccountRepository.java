package org.example.accountservice.repository;

import org.example.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCardNumber(String cardNumber);
    Account findAccountById(long id);
}
