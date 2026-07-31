package com.biblioteca.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

public class Multa {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    private boolean paga;

    private String motivo;

    @OneToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    public Multa() {
    }

    public Multa(double valor, String motivo, Emprestimo emprestimo) {
        this.valor = valor;
        this.motivo = motivo;
        this.emprestimo = emprestimo;
        this.paga = false;
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public void pagar() {
        this.paga = true;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", valor=" + valor +
                ", paga=" + paga +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
    

