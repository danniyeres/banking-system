package org.example.transactionservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private long id;

    private String ownerName;
    private Long ownerId;
    private double balance;

    private CardDTO card;
}

