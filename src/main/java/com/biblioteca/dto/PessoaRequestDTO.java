package com.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDTO(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank @Email String email,
        @NotBlank String telefone
) {
}