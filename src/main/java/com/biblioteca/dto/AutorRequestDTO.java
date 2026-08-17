package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequestDTO {

    @NotBlank(message = "O nome do autor é obrigatório")
    private String nome;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}