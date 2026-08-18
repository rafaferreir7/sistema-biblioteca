package com.biblioteca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.conversor.LeitorConversor;
import com.biblioteca.dto.LeitorResponseDTO;
import com.negocio.fachada.Fachada;

@RestController
@RequestMapping("/leitores")
public class LeitorController {

    private final Fachada fachada;
    private final LeitorConversor leitorConversor;

    public LeitorController(
            Fachada fachada,
            LeitorConversor leitorConversor) {

        this.fachada = fachada;
        this.leitorConversor = leitorConversor;
    }

    @GetMapping
    public ResponseEntity<List<LeitorResponseDTO>> listarLeitores() {

        List<LeitorResponseDTO> leitores = fachada.listarLeitores()
                .stream()
                .map(leitorConversor::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(leitores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeitorResponseDTO> buscarLeitor(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    leitorConversor.entityToResponse(
                            fachada.buscarLeitor(id)
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}