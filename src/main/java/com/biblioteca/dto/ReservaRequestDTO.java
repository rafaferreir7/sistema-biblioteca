package com.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReservaRequestDTO(
    @NotNull(message = "O ID do leitor é obrigatório.")
    Long leitorId,

    @NotNull(message = "O ID do livro é obrigatório.")
    Long livroId,

    @NotNull(message = "A data da reserva é obrigatória.")
    LocalDate dataReserva
) {}