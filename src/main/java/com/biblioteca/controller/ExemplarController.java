package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.model.Exemplar;
import com.negocio.fachada.Fachada;
import com.negocio.exception.ExemplarInvalidoException;
import com.negocio.exception.ExemplarNaoEncontradoException;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

    private final Fachada fachada;

    public ExemplarController(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Exemplar> cadastrar(
            @RequestBody Exemplar exemplar)
            throws ExemplarInvalidoException {

        return ResponseEntity.ok(
                fachada.cadastrarExemplar(exemplar)
        );
    }

    @GetMapping
    public ResponseEntity<List<Exemplar>> listarTodos() {
        return ResponseEntity.ok(
                fachada.listarExemplares()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exemplar> buscarPorId(
            @PathVariable Long id)
            throws ExemplarNaoEncontradoException {

        return ResponseEntity.ok(
                fachada.buscarExemplar(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id)
            throws ExemplarNaoEncontradoException {

        fachada.removerExemplar(id);

        return ResponseEntity.noContent().build();
    }
}
