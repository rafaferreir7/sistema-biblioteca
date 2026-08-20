package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.EmprestimoRequestDTO;
import com.biblioteca.dto.EmprestimoResponseDTO;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    // POST: Criar um novo empréstimo
    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@RequestBody EmprestimoRequestDTO request) {
        // Lógica de integração com a Fachada/Service entra aqui
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    // GET: Listar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos() {
        // Lógica de busca entra aqui
        return ResponseEntity.ok().build();
    }
    
    // PUT: Registrar a devolução de um livro
    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Void> registrarDevolucao(@PathVariable Long id) {
        // Lógica de atualização entra aqui
        return ResponseEntity.noContent().build();
    }
}