package org.example.transactionservice.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionNotify {
    private String email;
    private String message;
    private Date createdAt = new Date();
}
