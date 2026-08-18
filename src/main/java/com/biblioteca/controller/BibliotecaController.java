package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.model.Biblioteca;
import com.negocio.cadastro.BibliotecaCadastro;
import com.negocio.service.BibliotecaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping
    public ResponseEntity<Biblioteca> cadastrar(
            @Valid @RequestBody BibliotecaCadastro dto) {

        Biblioteca biblioteca = new Biblioteca(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone()
        );

        Biblioteca salva = bibliotecaService.cadastrar(biblioteca);

        return ResponseEntity.ok(salva);
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> listarTodas() {
        return ResponseEntity.ok(
                bibliotecaService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bibliotecaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody BibliotecaCadastro dto) {

        Biblioteca dados = new Biblioteca(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone()
        );

        return ResponseEntity.ok(
                bibliotecaService.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        bibliotecaService.remover(id);

        return ResponseEntity.noContent().build();
    }
}

