package com.negocio.fachada;

import java.util.List;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;

public interface BibliotecaFachada {
    Emprestimo realizarEmprestimoComplexo(Emprestimo emprestimo);
    List<Emprestimo> listarEmprestimos();
    Reserva criarReserva(Reserva reserva);
}