package org.example.authservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDTO {
    private String email;
    private String message;
    private final LocalDateTime createdAt = LocalDateTime.now();

}
