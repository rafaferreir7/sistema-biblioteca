package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MultaRequestDTO(
        @Positive double valor,
        @NotBlank String motivo,
        @NotNull Long emprestimoId
) {
}