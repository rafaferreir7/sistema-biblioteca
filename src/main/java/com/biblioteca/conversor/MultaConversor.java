package com.biblioteca.conversor;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.MultaRequestDTO;
import com.biblioteca.dto.MultaResponseDTO;
import com.biblioteca.model.Multa;

@Component
public class MultaConversor {

    public Multa requestToEntity(MultaRequestDTO dto) {
        Multa multa = new Multa();
        multa.setValor(dto.valor());
        multa.setMotivo(dto.motivo());
        return multa;
    }

    public MultaResponseDTO entityToResponse(Multa multa) {
        return new MultaResponseDTO(
                multa.getId(),
                multa.getValor(),
                multa.isPaga(),
                multa.getMotivo()
        );
    }
}