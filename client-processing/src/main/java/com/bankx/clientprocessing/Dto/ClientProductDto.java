package com.bankx.clientprocessing.Dto;

import com.bankx.clientprocessing.entity.Status;
import java.time.LocalDate;

public record ClientProductDto(
        Long id,
        Long clientId,
        Long productId,
        LocalDate openDate,
        LocalDate closeDate,
        Status status
) {}