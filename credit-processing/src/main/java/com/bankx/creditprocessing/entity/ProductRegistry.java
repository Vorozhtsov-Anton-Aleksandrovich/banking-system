package com.bankx.creditprocessing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_registry")
public class ProductRegistry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long client;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "open_date")
    private LocalDate openDate;
}