package com.bankx.clientprocessing.Dto;

import com.bankx.clientprocessing.entity.DocumentType;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientDto(
    Long id,
    Long clientId,
    Long userId,
    String firstName,
    String middleName,
    String lastName,
    @PastOrPresent
    LocalDate dateOfBirth,
    DocumentType documentType,
    Long documentId,
    String documentPrefix,
    String documentSuffix
) {}
