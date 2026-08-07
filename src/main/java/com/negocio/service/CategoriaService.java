package com.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Categoria;
import com.biblioteca.repository.CategoriaRepository;
import com.negocio.exception.CategoriaInvalidaException;
import com.negocio.exception.CategoriaNaoEncontradaException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria salvarCategoria(Categoria categoria) throws CategoriaInvalidaException {

        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new CategoriaInvalidaException("O nome da categoria é obrigatório.");
        }

        if (categoria.getDescricao() == null || categoria.getDescricao().isBlank()) {
            throw new CategoriaInvalidaException("A descrição da categoria é obrigatória.");
        }

        return categoriaRepository.save(categoria);
    }

    public Categoria buscarCategoria(Long id) throws CategoriaNaoEncontradaException {

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."));
    }

    public Categoria atualizarCategoria(Categoria categoria)
            throws CategoriaInvalidaException, CategoriaNaoEncontradaException {

        if (categoria.getId() == null) {
            throw new CategoriaNaoEncontradaException("A categoria deve possuir um ID para ser atualizada.");
        }

        if (!categoriaRepository.existsById(categoria.getId())) {
            throw new CategoriaNaoEncontradaException("Categoria não encontrada.");
        }

        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new CategoriaInvalidaException("O nome da categoria é obrigatório.");
        }

        if (categoria.getDescricao() == null || categoria.getDescricao().isBlank()) {
            throw new CategoriaInvalidaException("A descrição da categoria é obrigatória.");
        }

        return categoriaRepository.save(categoria);
    }

    public void removerCategoria(Long id) throws CategoriaNaoEncontradaException {

        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException("Categoria não encontrada.");
        }

        categoriaRepository.deleteById(id);
    }
}