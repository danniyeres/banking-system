package org.example.transactionservice.feign;

import org.example.transactionservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user/getById/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
