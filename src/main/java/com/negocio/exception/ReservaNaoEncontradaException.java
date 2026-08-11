package com.negocio.exception;

public class ReservaNaoEncontradaException extends RuntimeException {
    public ReservaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}