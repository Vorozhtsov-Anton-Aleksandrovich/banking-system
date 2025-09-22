package com.bankx.accountprocessing.Dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccountDto(
    Long id,
    Long clientId,
    Long productId,
    BigDecimal balance,
    BigDecimal interestRate,
    boolean isRecalc,
    boolean cardExist,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public AccountDto(Long clientId, Long productId) {
        this(
            null,
            clientId,
            productId,
            new BigDecimal(0),
            new BigDecimal(0),
            false,
            false,
            "NEW",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
}
