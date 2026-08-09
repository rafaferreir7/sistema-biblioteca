package com.biblioteca.exception;

public class MultaNaoEncontradaException extends RuntimeException {

    public MultaNaoEncontradaException(Long id) {
        super("Multa não encontrada com o ID: " + id);
    }
}