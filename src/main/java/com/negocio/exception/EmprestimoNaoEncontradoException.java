package com.negocio.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}