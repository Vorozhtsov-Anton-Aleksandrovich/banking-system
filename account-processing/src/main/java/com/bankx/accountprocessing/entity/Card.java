package com.bankx.accountprocessing.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "card_id", nullable = false, unique = true)
    private String cardId;

    @Column(name = "payment_system")
    private String paymentSystem;

    @Column(name = "status")
    private String status;

    @Column(name = "expire_date")
    private LocalDate expireDate;
}