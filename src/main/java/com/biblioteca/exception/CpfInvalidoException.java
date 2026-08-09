package com.biblioteca.exception;

public class CpfInvalidoException extends Exception {
    public CpfInvalidoException(String cpf) {
        super("O CPF informado (" + cpf + ") é inválido ou já está cadastrado.");
    }
}