package com.negocio.cadastro;

import java.util.List;

import com.biblioteca.model.Emprestimo;

public interface EmprestimoCadastro {
    Emprestimo realizarEmprestimo(Emprestimo emprestimo);
    Emprestimo buscarPorId(Long id);
    List<Emprestimo> listarTodos();
}