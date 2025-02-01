package org.example.authservice.feign;

import org.example.authservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service")
public interface UserFeign {

    @GetMapping("/user/getById/{id}")
    User findUserById(@PathVariable Long id);

    @GetMapping("/user/getByEmail/{email}")
    User findByEmail(@PathVariable String email);

    @PostMapping("/user/add")
    void addUser(User user);
}
