package org.example.authservice.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RegisterUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
