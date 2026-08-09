package com.negocio.fachada;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Bibliotecario;
import com.negocio.service.BibliotecaService;
import com.negocio.service.BibliotecarioService;

@Service
public class Fachada {

    private final BibliotecaService bibliotecaService;
    private final BibliotecarioService bibliotecarioService;

    public Fachada(
            BibliotecaService bibliotecaService,
            BibliotecarioService bibliotecarioService) {

        this.bibliotecaService = bibliotecaService;
        this.bibliotecarioService = bibliotecarioService;
    }

    // =========================
    // BIBLIOTECA
    // =========================

    public Biblioteca cadastrarBiblioteca(
            Biblioteca biblioteca) {

        return bibliotecaService.cadastrar(biblioteca);
    }

    public Biblioteca buscarBiblioteca(Long id) {

        return bibliotecaService.buscarPorId(id);
    }

    public List<Biblioteca> listarBibliotecas() {

        return bibliotecaService.listarTodas();
    }

    public Biblioteca atualizarBiblioteca(
            Long id,
            Biblioteca biblioteca) {

        return bibliotecaService.atualizar(
                id,
                biblioteca);
    }

    public void removerBiblioteca(Long id) {

        bibliotecaService.remover(id);
    }

    // =========================
    // BIBLIOTECARIO
    // =========================

    public Bibliotecario cadastrarBibliotecario(
            Bibliotecario bibliotecario) {

        return bibliotecarioService.cadastrar(
                bibliotecario);
    }

    public Bibliotecario buscarBibliotecario(Long id) {

        return bibliotecarioService.buscarPorId(id);
    }

    public List<Bibliotecario> listarBibliotecarios() {

        return bibliotecarioService.listarTodos();
    }

    public Bibliotecario atualizarBibliotecario(
            Long id,
            Bibliotecario bibliotecario) {

        return bibliotecarioService.atualizar(
                id,
                bibliotecario);
    }

    public void removerBibliotecario(Long id) {

        bibliotecarioService.remover(id);
    }
}
