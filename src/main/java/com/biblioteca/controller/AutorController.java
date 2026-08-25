package com.biblioteca.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.model.Autor;
import com.biblioteca.dto.AutorRequestDTO;
import com.negocio.fachada.BibliotecaFachada;
import com.negocio.exception.AutorInvalidoException;
import com.negocio.exception.AutorNaoEncontradoException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/autores")

public class AutorController {

    private final BibliotecaFachada fachada;

    public AutorController(BibliotecaFachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Autor> criarAutor(@Valid @RequestBody AutorRequestDTO dto) throws AutorInvalidoException {
        Autor autor = montarAutor(dto);
        Autor novoAutor = fachada.cadastrarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutor);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(fachada.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable Long id) throws AutorNaoEncontradoException {
        return ResponseEntity.ok(fachada.buscarAutor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto)
            throws AutorInvalidoException, AutorNaoEncontradoException {
        Autor autor = montarAutor(dto);
        autor.setId(id);
        return ResponseEntity.ok(fachada.atualizarAutor(autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAutor(@PathVariable Long id) throws AutorNaoEncontradoException {
        fachada.removerAutor(id);
        return ResponseEntity.noContent().build();
    }

    private Autor montarAutor(AutorRequestDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        return autor;
    }
}