package com.negocio.cadastro;

import java.util.List;

import com.biblioteca.exception.MultaJaPagaException;
import com.biblioteca.model.Multa;

public interface MultaCadastroInterface {

    Multa criarMulta(Multa multa);

    Multa pagarMulta(Long id) throws MultaJaPagaException;

    List<Multa> listarTodas();

    void excluirMulta(Long id);
}