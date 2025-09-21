package com.bankx.clientprocessing.repository;

import com.bankx.clientprocessing.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
