package com.biblioteca.controller;

import com.biblioteca.model.Bibliotecario;
import com.negocio.service.BibliotecarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliotecarios")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;

    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bibliotecario cadastrar(@RequestBody @Valid Bibliotecario bibliotecario) {
        return bibliotecarioService.cadastrar(bibliotecario);
    }

    @GetMapping
    public List<Bibliotecario> listarTodos() {
        return bibliotecarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Bibliotecario buscarPorId(@PathVariable Long id) {
        return bibliotecarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Bibliotecario atualizar(@PathVariable Long id,
                                   @RequestBody @Valid Bibliotecario bibliotecario) {
        return bibliotecarioService.atualizar(id, bibliotecario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        bibliotecarioService.remover(id);
    }
}