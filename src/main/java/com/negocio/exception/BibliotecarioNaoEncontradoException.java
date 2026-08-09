package com.negocio.exception;

public class BibliotecarioNaoEncontradoException
        extends RuntimeException {

    public BibliotecarioNaoEncontradoException(Long id) {
        super("Bibliotecário não encontrado com o ID: " + id);
    }
}
