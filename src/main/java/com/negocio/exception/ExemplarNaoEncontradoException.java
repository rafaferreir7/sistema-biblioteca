package com.negocio.exception;

public class ExemplarNaoEncontradoException extends Exception {

    public ExemplarNaoEncontradoException(Long id) {
        super("Exemplar não encontrado: " + id);
    }
}