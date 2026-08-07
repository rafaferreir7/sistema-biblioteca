package com.negocio.exception;

public class LivroNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}