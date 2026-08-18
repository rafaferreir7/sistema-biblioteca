package com.biblioteca.dto;

public record PessoaResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {
}