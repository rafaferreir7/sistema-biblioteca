package com.negocio.exception;

public class AutorInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public AutorInvalidoException(String mensagem) {
        super(mensagem);
    }
}