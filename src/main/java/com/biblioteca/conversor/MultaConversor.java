package com.biblioteca.conversor;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.MultaResponseDTO;
import com.biblioteca.model.Multa;

@Component
public class MultaConversor {

    public MultaResponseDTO entityToResponse(Multa multa) {
        return new MultaResponseDTO(
                multa.getId(),
                multa.getValor(),
                multa.isPaga(),
                multa.getMotivo()
        );
    }
}