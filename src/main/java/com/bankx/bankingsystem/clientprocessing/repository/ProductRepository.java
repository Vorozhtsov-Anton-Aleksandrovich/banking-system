package com.bankx.bankingsystem.clientprocessing.repository;

import com.bankx.bankingsystem.clientprocessing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<User, Long> {}
