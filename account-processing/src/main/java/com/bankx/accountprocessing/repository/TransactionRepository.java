package com.bankx.accountprocessing.repository;

import com.bankx.accountprocessing.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {}
