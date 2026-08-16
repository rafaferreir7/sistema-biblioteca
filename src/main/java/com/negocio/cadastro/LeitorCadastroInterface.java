package com.negocio.cadastro;

import java.util.List;

import com.biblioteca.exception.CpfInvalidoException;
import com.biblioteca.exception.LeitorNaoEncontradoException;
import com.biblioteca.model.Leitor;

public interface LeitorCadastroInterface {

    Leitor cadastrarLeitor(Leitor leitor) throws CpfInvalidoException;

    Leitor buscarPorId(Long id) throws LeitorNaoEncontradoException;

    List<Leitor> listarTodos();

    void excluirLeitor(Long id) throws LeitorNaoEncontradoException;
}