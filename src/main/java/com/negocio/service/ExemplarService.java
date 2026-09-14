package com.negocio.service;

import com.biblioteca.model.Exemplar;
import com.biblioteca.repository.ExemplarRepository;
import com.negocio.exception.ExemplarInvalidoException;
import com.negocio.exception.ExemplarNaoEncontradoException;
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

    public Exemplar salvar(Exemplar exemplar) throws ExemplarInvalidoException {
        if (exemplar == null) {
            throw new ExemplarInvalidoException("O exemplar não pode ser nulo");
        }

        if (exemplar.getCodigoPatrimonio() == null ||
            exemplar.getCodigoPatrimonio().isBlank()) {
            throw new ExemplarInvalidoException("O código de patrimônio é obrigatório");
        }

        if (exemplar.getLivro() == null) {
            throw new ExemplarInvalidoException("O livro do exemplar é obrigatório");
        }

        if (exemplar.getLocalizacao() == null) {
            throw new ExemplarInvalidoException("A localização do exemplar é obrigatória");
        }

        return exemplarRepository.save(exemplar);
    }

    public Exemplar buscarPorId(Long id) throws ExemplarNaoEncontradoException {
        return exemplarRepository.findById(id)
                .orElseThrow(() ->
                        new ExemplarNaoEncontradoException(id));
    }

    public void remover(Long id) throws ExemplarNaoEncontradoException {
        if (!exemplarRepository.existsById(id)) {
            throw new ExemplarNaoEncontradoException(id);
        }

        exemplarRepository.deleteById(id);
    }
}