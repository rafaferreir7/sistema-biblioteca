package com.negocio.service;

import com.biblioteca.exception.BibliotecaNaoEncontradaException;
import com.biblioteca.model.Biblioteca;
import com.biblioteca.repository.BibliotecaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public Biblioteca cadastrar(Biblioteca biblioteca) {

        if (biblioteca == null) {
            throw new IllegalArgumentException("Biblioteca não pode ser nula");
        }

        if (biblioteca.getNome() == null || biblioteca.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da biblioteca é obrigatório");
        }

        if (biblioteca.getEmail() == null || biblioteca.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email da biblioteca é obrigatório");
        }

        if (biblioteca.getTelefone() == null || biblioteca.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone da biblioteca é obrigatório");
        }

        return bibliotecaRepository.save(biblioteca);
    }

    public Biblioteca buscarPorId(Long id) {

        return bibliotecaRepository.findById(id)
                .orElseThrow(() -> new BibliotecaNaoEncontradaException(id));
    }

    public List<Biblioteca> listarTodas() {
        return bibliotecaRepository.findAll();
    }

    public Biblioteca atualizar(Long id, Biblioteca dados) {

        Biblioteca biblioteca = buscarPorId(id);

        if (dados.getNome() == null || dados.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da biblioteca é obrigatório");
        }

        if (dados.getEmail() == null || dados.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email da biblioteca é obrigatório");
        }

        if (dados.getTelefone() == null || dados.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone da biblioteca é obrigatório");
        }

        biblioteca.setNome(dados.getNome());
        biblioteca.setEmail(dados.getEmail());
        biblioteca.setTelefone(dados.getTelefone());

        return bibliotecaRepository.save(biblioteca);
    }

    public void remover(Long id) {

        Biblioteca biblioteca = buscarPorId(id);

        bibliotecaRepository.delete(biblioteca);
    }
}
