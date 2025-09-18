package com.bankx.bankingsystem.accountprocessing.repository;

import com.bankx.bankingsystem.accountprocessing.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {}