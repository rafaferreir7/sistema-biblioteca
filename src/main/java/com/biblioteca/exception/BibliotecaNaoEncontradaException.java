package com.biblioteca.exception;

public class BibliotecaNaoEncontradaException extends RuntimeException {

    public BibliotecaNaoEncontradaException(Long id) {
        super("Biblioteca não encontrada com o ID: " + id);
    }
}
