package org.example.notificationservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionNotify {
    private String email;
    private String message;

    private String createdAt;
}
