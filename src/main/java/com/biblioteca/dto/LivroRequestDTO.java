package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.util.List;

public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;

    private int ano;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private int quantidade;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;

    @NotNull(message = "Pelo menos um autor é obrigatório")
    private List<Long> autorIds;

    // getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public List<Long> getAutorIds() { return autorIds; }
    public void setAutorIds(List<Long> autorIds) { this.autorIds = autorIds; }
}