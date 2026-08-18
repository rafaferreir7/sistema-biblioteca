package com.biblioteca.exception;

public class MultaJaPagaException extends RuntimeException {

    public MultaJaPagaException(Long id) {
        super("A multa com ID " + id + " já se encontra paga.");
    }

    public MultaJaPagaException(String mensagem) {
        super(mensagem);
    }
}