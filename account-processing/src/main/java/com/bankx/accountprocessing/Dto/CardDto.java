package com.bankx.accountprocessing.Dto;

import com.bankx.accountprocessing.entity.enums.CardStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record CardDto(
    Long id,
    Long accountId,
    String cardId,
    String paymentSystem,
    CardStatus cardStatus,
    LocalDate expireDate
) {}
