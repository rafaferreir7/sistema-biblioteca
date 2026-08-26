package com.biblioteca.dto;

import java.time.LocalDate;

public record ReservaResponseDTO(
        Long id,
        Long leitorId,
        Long livroId,
        LocalDate dataReserva,
        boolean ativa
) {}