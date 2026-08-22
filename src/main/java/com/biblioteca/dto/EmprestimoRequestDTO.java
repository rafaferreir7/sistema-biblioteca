package com.biblioteca.dto;

import java.time.LocalDate;

public class EmprestimoRequestDTO {
    
    private Long leitorId;
    private Long livroId;
    private LocalDate dataDevolucaoPrevista;

    public EmprestimoRequestDTO() {
    }

    public Long getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(Long leitorId) {
        this.leitorId = leitorId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
}