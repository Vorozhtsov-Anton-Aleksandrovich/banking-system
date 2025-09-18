package com.bankx.bankingsystem.accountprocessing.repository;

import com.bankx.bankingsystem.accountprocessing.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByCardId(Long cardId);
    List<Transaction> findByStatus(String status);
    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}