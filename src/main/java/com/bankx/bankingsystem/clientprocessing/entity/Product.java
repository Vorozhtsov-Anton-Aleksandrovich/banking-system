package com.bankx.bankingsystem.clientprocessing.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductKey key;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "product_id")
    private String productId;
}
