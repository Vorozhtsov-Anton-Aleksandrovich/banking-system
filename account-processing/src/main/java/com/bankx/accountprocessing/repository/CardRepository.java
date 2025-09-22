package com.bankx.accountprocessing.repository;

import com.bankx.accountprocessing.entity.AccountEntity;
import com.bankx.accountprocessing.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {}
