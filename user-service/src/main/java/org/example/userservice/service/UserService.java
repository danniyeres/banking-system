package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO findByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null)
            return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

    }

    public UserDTO findById(Long id) {
        var user = userRepository.findById(id).orElseThrow();

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

    }

    public void addUser(UserDTO userDTO) {
        if (existsByEmail(userDTO.getEmail()))
            throw new IllegalArgumentException("User with this email already exists");

        var user = User.builder()
            .firstName(userDTO.getFirstName())
            .lastName(userDTO.getLastName())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
            .build();
        userRepository.save(user);
        log.info("added user {}", user);
    }

    public void updateUser(UserDTO userDTO) {
        var user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null)
            throw new IllegalArgumentException("User with this email not found");

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
        log.info("updated user {}", user);
    }
}
