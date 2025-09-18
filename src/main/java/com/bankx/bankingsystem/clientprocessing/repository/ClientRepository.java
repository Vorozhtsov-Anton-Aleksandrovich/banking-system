package com.bankx.bankingsystem.clientprocessing.repository;

import com.bankx.bankingsystem.clientprocessing.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}