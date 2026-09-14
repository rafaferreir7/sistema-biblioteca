package com.negocio.exception;

public class LocalizacaoNaoEncontradaException extends Exception {

    public LocalizacaoNaoEncontradaException(Long id) {
        super("Localização não encontrada: " + id);
    }
}