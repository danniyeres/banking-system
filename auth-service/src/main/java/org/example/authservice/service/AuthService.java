package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.LoginUser;
import org.example.authservice.dto.RegisterUser;
import org.example.authservice.feign.UserFeign;
import org.example.authservice.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserFeign userFeign;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterUser registerUser) {
        if (userFeign.findByEmail(registerUser.getEmail()) != null)
            throw new IllegalArgumentException("Username is already taken");

        var user = User.builder()
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .build();
        userFeign.addUser(user);
        log.info("User registered: {}", user.toString());
        return jwtService.generateToken(user.getEmail());
    }

    public String login(LoginUser loginUser) {
        var user = userFeign.findByEmail(loginUser.getEmail());
        if (user == null)
            throw new IllegalArgumentException("user not found");

        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Invalid username or password");

        return jwtService.generateToken(user.getEmail());
    }
}
