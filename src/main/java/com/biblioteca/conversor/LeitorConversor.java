package com.biblioteca.conversor;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.LeitorResponseDTO;
import com.biblioteca.model.Leitor;

@Component
public class LeitorConversor {

    public LeitorResponseDTO entityToResponse(Leitor leitor) {
        return new LeitorResponseDTO(
                leitor.getId(),
                leitor.getNome(),
                leitor.getCpf(),
                leitor.getEmail(),
                leitor.getTelefone(),
                leitor.getMatricula(),
                leitor.getDataCadastro(),
                leitor.isAtivo()
        );
    }
}