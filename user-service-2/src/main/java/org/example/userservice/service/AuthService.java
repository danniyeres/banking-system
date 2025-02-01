package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userservice.dto.LoginUser;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String register(UserDTO userDTO) {
        if (repository.findByEmail(userDTO.getEmail()) != null)
            throw new IllegalArgumentException("Username is already taken");

        var user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        repository.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    public String login(LoginUser loginUser) {
        var user = repository.findByEmail(loginUser.getEmail());
        if (user == null || !passwordEncoder.matches(loginUser.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Invalid username or password");

        return jwtService.generateToken(user.getEmail());
    }
}
