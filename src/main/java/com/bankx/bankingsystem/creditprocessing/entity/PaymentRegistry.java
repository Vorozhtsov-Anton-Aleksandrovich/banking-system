package com.bankx.bankingsystem.creditprocessing.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment_registry")
public class PaymentRegistry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_registry_id", nullable = false)
    private ProductRegistry productRegistry;

    @Column(name = "payment_date")
    private LocalDate paymendDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "interest_rate_amount")
    private BigDecimal interestRateAmount;

    @Column(name = "debt_amount")
    private BigDecimal debtAmount;

    @Column(nullable = false)
    private boolean expired = false;

    @Column(name = "payment_expiration_date")
    private LocalDate paymentExpirationDate;
}