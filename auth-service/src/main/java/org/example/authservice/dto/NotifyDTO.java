package org.example.authservice.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDTO {
    private String email;
    private String message;
    private Date createdAt = new Date();

}
