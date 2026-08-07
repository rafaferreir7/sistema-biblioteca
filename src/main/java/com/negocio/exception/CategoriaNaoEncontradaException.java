package com.negocio.exception;

public class CategoriaNaoEncontradaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoriaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}