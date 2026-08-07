package com.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import com.negocio.exception.LivroInvalidoException;
import com.negocio.exception.LivroNaoEncontradoException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro salvarLivro(Livro livro) throws LivroInvalidoException {

        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new LivroInvalidoException("O título do livro é obrigatório.");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isBlank()) {
            throw new LivroInvalidoException("O ISBN do livro é obrigatório.");
        }

        if (livro.getQuantidade() < 0) {
            throw new LivroInvalidoException("A quantidade de exemplares não pode ser negativa.");
        }

        if (livro.getCategoria() == null) {
            throw new LivroInvalidoException("O livro deve possuir uma categoria.");
        }

        if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
            throw new LivroInvalidoException("O livro deve possuir pelo menos um autor.");
        }

        return livroRepository.save(livro);
    }

    public Livro buscarLivro(Long id) throws LivroNaoEncontradoException {

        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));
    }

    public void removerLivro(Long id) throws LivroNaoEncontradoException {

        if (!livroRepository.existsById(id)) {
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }

        livroRepository.deleteById(id);
    }

    public Livro atualizarLivro(Livro livro)
            throws LivroInvalidoException, LivroNaoEncontradoException {

        if (livro.getId() == null) {
            throw new LivroNaoEncontradoException("O livro deve possuir um ID para ser atualizado.");
        }

        if (!livroRepository.existsById(livro.getId())) {
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }

        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new LivroInvalidoException("O título do livro é obrigatório.");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isBlank()) {
            throw new LivroInvalidoException("O ISBN do livro é obrigatório.");
        }

        if (livro.getQuantidade() < 0) {
            throw new LivroInvalidoException("A quantidade de exemplares não pode ser negativa.");
        }

        if (livro.getCategoria() == null) {
            throw new LivroInvalidoException("O livro deve possuir uma categoria.");
        }

        if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
            throw new LivroInvalidoException("O livro deve possuir pelo menos um autor.");
        }

        return livroRepository.save(livro);
    }
}
