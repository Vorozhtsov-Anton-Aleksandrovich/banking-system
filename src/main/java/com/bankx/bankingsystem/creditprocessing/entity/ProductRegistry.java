package com.bankx.bankingsystem.creditprocessing.entity;

import com.bankx.bankingsystem.accountprocessing.entity.Account;
import com.bankx.bankingsystem.clientprocessing.entity.Client;
import com.bankx.bankingsystem.clientprocessing.entity.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_registry")
public class ProductRegistry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account  account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "open_date")
    private LocalDate openDate;
}