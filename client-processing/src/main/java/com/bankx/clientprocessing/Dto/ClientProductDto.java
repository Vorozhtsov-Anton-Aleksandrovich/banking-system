package com.bankx.clientprocessing.Dto;

import com.bankx.clientprocessing.entity.enums.ClientProductStatus;
import java.time.LocalDate;

public record ClientProductDto(
        Long id,
        Long clientId,
        Long productId,
        LocalDate openDate,
        LocalDate closeDate,
        ClientProductStatus status
) {}