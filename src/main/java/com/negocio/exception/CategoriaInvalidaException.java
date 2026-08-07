package com.negocio.exception;

public class CategoriaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoriaInvalidaException(String mensagem) {
        super(mensagem);
    }
}