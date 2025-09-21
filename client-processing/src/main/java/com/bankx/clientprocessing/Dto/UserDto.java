package com.bankx.clientprocessing.Dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(
    Long id,
    @NotNull
    String login,
    @NotNull
    String password,
    @NotNull
    String email
){}
