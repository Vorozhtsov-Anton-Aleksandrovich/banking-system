package com.bankx.accountprocessing.repository;


import com.bankx.accountprocessing.entity.AccountEntity;
import com.bankx.clientprocessing.entity.ClientProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByClientId(Long clientId);
}
