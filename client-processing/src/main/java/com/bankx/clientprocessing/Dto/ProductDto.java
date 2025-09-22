package com.bankx.clientprocessing.Dto;

import com.bankx.clientprocessing.entity.enums.ProductKey;
import java.time.LocalDate;

public record ProductDto(
    Long id,
    String name,
    ProductKey key,
    LocalDate createDate,
    Long productId
)
{}
