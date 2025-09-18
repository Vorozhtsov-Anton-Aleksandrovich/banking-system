package com.bankx.bankingsystem.clientprocessing.repository;

import com.bankx.bankingsystem.clientprocessing.entity.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {}
