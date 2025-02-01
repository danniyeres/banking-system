package org.example.accountservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String ownerName;
    private Long ownerId;
    private double balance;

    @OneToOne (mappedBy = "account",cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    private Card card;
}
