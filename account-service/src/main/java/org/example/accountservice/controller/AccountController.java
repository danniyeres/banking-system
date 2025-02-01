package org.example.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.model.Account;
import org.example.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PostMapping("/createWithUserID/{userId}")
    public Account createAccountWithUserId(@PathVariable Long userId) {
        return accountService.createAccountWithUserId(userId);
    }

    @GetMapping("/get/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @PostMapping("/deposit/{accountId}")
    public void deposit(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw/{accountId}")
    public void withdraw(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.withdraw(accountId, amount);
    }
}
