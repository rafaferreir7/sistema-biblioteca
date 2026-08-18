package com.biblioteca.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Bibliotecario extends Pessoa {
    
    @NotBlank(message = "Cargo é obrigatório")
private String cargo;

@NotNull(message = "Data de admissão é obrigatória")
private LocalDate dataAdmissao;

    public void cadastrarLivro(Livro livro) {
    }

    public void cadastrarUsuario(Pessoa p) {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}