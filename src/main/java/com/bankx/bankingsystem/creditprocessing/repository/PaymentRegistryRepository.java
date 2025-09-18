package com.bankx.bankingsystem.creditprocessing.repository;

import com.bankx.bankingsystem.clientprocessing.entity.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRegistryRepository extends JpaRepository<ClientProduct, Long> {}
