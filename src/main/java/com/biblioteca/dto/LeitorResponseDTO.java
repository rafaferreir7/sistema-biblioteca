package com.biblioteca.dto;

import java.time.LocalDate;

public record LeitorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String matricula,
        LocalDate dataCadastro,
        boolean ativo
) {
}