package com.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import com.biblioteca.model.Pessoa;

public interface PessoaCadastroInterface {

    List<Pessoa> listarTodas();

    Optional<Pessoa> buscarPorId(Long id);

    void excluirPessoa(Long id);
}