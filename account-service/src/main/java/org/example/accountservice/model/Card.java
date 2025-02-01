package org.example.accountservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String number; // 16 digits - 40001234 + 12 random digits
    private String expiryDate; // MM/YY
    private int cvv; // 3 digits - 000 to 999 random

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
}
