package com.biblioteca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.conversor.MultaConversor;
import com.biblioteca.dto.MultaResponseDTO;
import com.negocio.fachada.Fachada;

@RestController
@RequestMapping("/multas")
public class MultaController {

    private final Fachada fachada;
    private final MultaConversor multaConversor;

    public MultaController(
            Fachada fachada,
            MultaConversor multaConversor) {

        this.fachada = fachada;
        this.multaConversor = multaConversor;
    }

    @GetMapping
    public ResponseEntity<List<MultaResponseDTO>> listarMultas() {

        List<MultaResponseDTO> multas = fachada.listarMultas()
                .stream()
                .map(multaConversor::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(multas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaResponseDTO> buscarMulta(
            @PathVariable Long id) {

        return fachada.listarMultas()
                .stream()
                .filter(multa -> multa.getId().equals(id))
                .findFirst()
                .map(multaConversor::entityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}