package com.bankx.bankingsystem.accountprocessing.repository;

import com.bankx.bankingsystem.accountprocessing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}