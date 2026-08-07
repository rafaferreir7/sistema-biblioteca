package com.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Autor;
import com.biblioteca.repository.AutorRepository;
import com.negocio.exception.AutorInvalidoException;
import com.negocio.exception.AutorNaoEncontradoException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public Autor salvarAutor(Autor autor) throws AutorInvalidoException {

        if (autor.getNome() == null || autor.getNome().isBlank()) {
            throw new AutorInvalidoException("O nome do autor é obrigatório.");
        }

        return autorRepository.save(autor);
    }

    public Autor buscarAutor(Long id) throws AutorNaoEncontradoException {

        return autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));
    }

    public Autor atualizarAutor(Autor autor)
            throws AutorInvalidoException, AutorNaoEncontradoException {

        if (autor.getId() == null) {
            throw new AutorNaoEncontradoException("O autor deve possuir um ID para ser atualizado.");
        }

        if (!autorRepository.existsById(autor.getId())) {
            throw new AutorNaoEncontradoException("Autor não encontrado.");
        }

        if (autor.getNome() == null || autor.getNome().isBlank()) {
            throw new AutorInvalidoException("O nome do autor é obrigatório.");
        }

        return autorRepository.save(autor);
    }

    public void removerAutor(Long id) throws AutorNaoEncontradoException {

        if (!autorRepository.existsById(id)) {
            throw new AutorNaoEncontradoException("Autor não encontrado.");
        }

        autorRepository.deleteById(id);
    }
}