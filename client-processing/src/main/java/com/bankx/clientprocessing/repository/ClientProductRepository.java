package com.bankx.clientprocessing.repository;

import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientProductRepository extends JpaRepository<ClientProductEntity, Long> {
    List<ClientProductEntity> findByClientId(Long clientId);
    List<ClientProductEntity> findByProductId(Long productId);
    List<ClientProductEntity> findByStatus(Status status);
}