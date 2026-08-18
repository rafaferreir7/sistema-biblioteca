package com.biblioteca.dto;

public record MultaResponseDTO(
        Long id,
        double valor,
        boolean paga,
        String motivo
) {
}