package org.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.userservice.dto.LoginUser;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.service.AuthService;
import org.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUser loginUser) {
        return authService.login(loginUser);
    }

    @GetMapping("/user/get/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
