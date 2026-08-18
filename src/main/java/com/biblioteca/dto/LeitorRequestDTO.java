package com.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LeitorRequestDTO(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank String matricula,
        @NotNull LocalDate dataCadastro,
        boolean ativo
) {
}