package com.negocio.service;

import com.biblioteca.model.Exemplar;
import com.biblioteca.repository.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplarService {

    @Autowired
    private ExemplarRepository exemplarRepository;

    public List<Exemplar> listarTodos() {
        return exemplarRepository.findAll();
    }

    public Exemplar salvar(Exemplar exemplar) {
        if (exemplar == null) {
            throw new IllegalArgumentException("O exemplar não pode ser nulo");
        }

        if (exemplar.getCodigoPatrimonio() == null ||
            exemplar.getCodigoPatrimonio().isBlank()) {
            throw new IllegalArgumentException("O código de patrimônio é obrigatório");
        }

        if (exemplar.getLivro() == null) {
            throw new IllegalArgumentException("O livro do exemplar é obrigatório");
        }

        if (exemplar.getLocalizacao() == null) {
            throw new IllegalArgumentException("A localização do exemplar é obrigatória");
        }

        return exemplarRepository.save(exemplar);
    }

    public Exemplar buscarPorId(Long id) {
        return exemplarRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exemplar não encontrado"));
    }

    public void remover(Long id) {
        if (!exemplarRepository.existsById(id)) {
            throw new IllegalArgumentException("Exemplar não encontrado");
        }

        exemplarRepository.deleteById(id);
    }
}