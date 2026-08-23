package com.biblioteca.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Categoria;
import com.biblioteca.model.Autor;
import com.biblioteca.dto.LivroRequestDTO;
import com.negocio.fachada.BibliotecaFachada;
import com.negocio.exception.LivroInvalidoException;
import com.negocio.exception.LivroNaoEncontradoException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/livros")

public class LivroController {

    private final BibliotecaFachada fachada;

    public LivroController(BibliotecaFachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody LivroRequestDTO dto) throws LivroInvalidoException {
        Livro livro = montarLivro(dto);
        Livro novoLivro = fachada.cadastrarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(fachada.listarLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id) throws LivroNaoEncontradoException {
        return ResponseEntity.ok(fachada.buscarLivro(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto)
            throws LivroInvalidoException, LivroNaoEncontradoException {
        Livro livro = montarLivro(dto);
        livro.setId(id);
        return ResponseEntity.ok(fachada.atualizarLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) throws LivroNaoEncontradoException {
        fachada.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    private Livro montarLivro(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAno(dto.getAno());
        livro.setQuantidade(dto.getQuantidade());

        Categoria categoria = new Categoria();
        categoria.setId(dto.getCategoriaId());
        livro.setCategoria(categoria);

        List<Autor> autores = dto.getAutorIds().stream()
                .map(id -> {
                    Autor a = new Autor();
                    a.setId(id);
                    return a;
                })
                .collect(Collectors.toList());
        livro.setAutores(autores);

        return livro;
    }
}