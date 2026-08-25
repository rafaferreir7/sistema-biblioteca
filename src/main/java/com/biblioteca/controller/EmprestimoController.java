package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.model.Emprestimo;
import com.negocio.service.EmprestimoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> cadastrar(
            @Valid @RequestBody EmprestimoRequestDTO dto) {

        Emprestimo emprestimo = emprestimoService.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {

        return ResponseEntity.ok(
                emprestimoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emprestimoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Emprestimo> registrarDevolucao(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                emprestimoService.registrarDevolucao(id)
        );
    }
}