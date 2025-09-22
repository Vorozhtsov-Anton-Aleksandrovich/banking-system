package com.bankx.accountprocessing.entity;

import com.bankx.accountprocessing.entity.enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@Getter @Setter
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "card_id", nullable = false, unique = true)
    private String cardId;

    @Column(name = "payment_system")
    private String paymentSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;

    @Column(name = "expire_date")
    private LocalDate expireDate;
}