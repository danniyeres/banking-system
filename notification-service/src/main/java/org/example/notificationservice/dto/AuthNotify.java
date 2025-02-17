package org.example.notificationservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthNotify {
    private String email;
    private String message;

    private String createdAt;

    public AuthNotify(String email, String message, String createdAt) {
        this.email = email;
        this.message = message;
        this.createdAt = createdAt;
    }

    public AuthNotify() {
    }
}