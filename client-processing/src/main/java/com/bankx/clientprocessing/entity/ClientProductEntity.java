package com.bankx.clientprocessing.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client_products")
public class ClientProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
