package com.biblioteca.exception;

public class LeitorNaoEncontradoException extends Exception {
    public LeitorNaoEncontradoException(Long id) {
        super("Leitor com ID " + id + " não foi encontrado.");
    }
}