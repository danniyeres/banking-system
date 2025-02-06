package org.example.transactionservice.feign;

import org.example.transactionservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")
public interface AccountClient {
    @GetMapping("/account/get/{accountId}")
    AccountDTO getAccount(@PathVariable Long accountId);

    @PostMapping("/account/deposit/{accountId}")
    void deposit(@PathVariable Long accountId, @RequestParam double amount) ;

    @PostMapping("/account/withdraw/{accountId}")
    void withdraw(@PathVariable Long accountId, @RequestParam double amount) ;
}
