package com.biblioteca.conversor;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.PessoaResponseDTO;
import com.biblioteca.model.Pessoa;

@Component
public class PessoaConversor {

    public PessoaResponseDTO entityToResponse(Pessoa pessoa) {
        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail(),
                pessoa.getTelefone()
        );
    }
}