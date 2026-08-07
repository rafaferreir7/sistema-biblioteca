package com.negocio.exception;

public class AutorNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public AutorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}