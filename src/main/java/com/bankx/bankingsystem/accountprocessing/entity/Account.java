package com.bankx.bankingsystem.accountprocessing.entity;

import com.bankx.bankingsystem.clientprocessing.entity.Client;
import com.bankx.bankingsystem.clientprocessing.entity.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "is_recalc", nullable = false)
    private boolean isRecalc = false;

    @Column(name = "card_exist", nullable = false)
    private boolean cardExist = false;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}