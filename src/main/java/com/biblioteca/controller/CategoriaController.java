package com.biblioteca.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.model.Categoria;
import com.biblioteca.dto.CategoriaRequestDTO;
import com.negocio.fachada.BibliotecaFachada;
import com.negocio.exception.CategoriaInvalidaException;
import com.negocio.exception.CategoriaNaoEncontradaException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categorias")

public class CategoriaController {

    private final BibliotecaFachada fachada;

    public CategoriaController(BibliotecaFachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody CategoriaRequestDTO dto) throws CategoriaInvalidaException {
        Categoria categoria = montarCategoria(dto);
        Categoria novaCategoria = fachada.cadastrarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(fachada.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id) throws CategoriaNaoEncontradaException {
        return ResponseEntity.ok(fachada.buscarCategoria(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto)
            throws CategoriaInvalidaException, CategoriaNaoEncontradaException {
        Categoria categoria = montarCategoria(dto);
        categoria.setId(id);
        return ResponseEntity.ok(fachada.atualizarCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCategoria(@PathVariable Long id) throws CategoriaNaoEncontradaException {
        fachada.removerCategoria(id);
        return ResponseEntity.noContent().build();
    }

    private Categoria montarCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return categoria;
    }
}