package com.bankx.clientprocessing.repository;

import com.bankx.clientprocessing.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    Optional<ProductEntity> findByProductId(Long productId);

    @Query("SELECT p FROM ProductEntity p WHERE p.key = :key")
    List<ProductEntity> findByProductKey(@Param("key") String key);

    List<ProductEntity> findByCreateDateAfter(java.time.LocalDate date);
}