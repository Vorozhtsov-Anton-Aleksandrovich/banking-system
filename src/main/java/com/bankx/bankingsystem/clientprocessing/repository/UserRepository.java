package com.bankx.bankingsystem.clientprocessing.repository;

import com.bankx.bankingsystem.clientprocessing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}