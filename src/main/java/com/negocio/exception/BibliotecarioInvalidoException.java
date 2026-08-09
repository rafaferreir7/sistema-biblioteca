package com.negocio.exception;

public class BibliotecarioInvalidoException
        extends RuntimeException {

    public BibliotecarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}