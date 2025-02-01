package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.LoginUser;
import org.example.authservice.dto.RegisterUser;
import org.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterUser registerUser) {
        return authService.register(registerUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUser loginUser) {
        return authService.login(loginUser);
    }
}
