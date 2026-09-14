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

import com.biblioteca.model.Localizacao;
import com.negocio.fachada.Fachada;
import com.negocio.exception.LocalizacaoInvalidaException;
import com.negocio.exception.LocalizacaoNaoEncontradaException;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    private final Fachada fachada;

    public LocalizacaoController(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Localizacao> cadastrar(
            @RequestBody Localizacao localizacao)
            throws LocalizacaoInvalidaException {

        return ResponseEntity.ok(
                fachada.cadastrarLocalizacao(localizacao)
        );
    }

    @GetMapping
    public ResponseEntity<List<Localizacao>> listarTodas() {
        return ResponseEntity.ok(
                fachada.listarLocalizacoes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> buscarPorId(
            @PathVariable Long id)
            throws LocalizacaoNaoEncontradaException {

        return ResponseEntity.ok(
                fachada.buscarLocalizacao(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id)
            throws LocalizacaoNaoEncontradaException {

        fachada.removerLocalizacao(id);

        return ResponseEntity.noContent().build();
    }
}