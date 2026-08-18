package com.biblioteca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.conversor.PessoaConversor;
import com.biblioteca.dto.PessoaResponseDTO;
import com.biblioteca.model.Pessoa;
import com.negocio.fachada.Fachada;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final Fachada fachada;
    private final PessoaConversor pessoaConversor;

    public PessoaController(
            Fachada fachada,
            PessoaConversor pessoaConversor) {

        this.fachada = fachada;
        this.pessoaConversor = pessoaConversor;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas() {

        List<PessoaResponseDTO> pessoas = fachada.listarPessoas()
                .stream()
                .map(pessoaConversor::entityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarPessoa(
            @PathVariable Long id) {

        return fachada.buscarPessoa(id)
                .map(pessoaConversor::entityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}