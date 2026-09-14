package com.negocio.service;

import com.biblioteca.model.Localizacao;
import com.biblioteca.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<Localizacao> listarTodas() {
        return localizacaoRepository.findAll();
    }

    public Localizacao salvar(Localizacao localizacao) {
        if (localizacao == null) {
            throw new IllegalArgumentException("A localização não pode ser nula");
        }

        if (localizacao.getEstante() == null ||
            localizacao.getEstante().isBlank()) {
            throw new IllegalArgumentException("A estante é obrigatória");
        }

        if (localizacao.getPrateleira() == null ||
            localizacao.getPrateleira().isBlank()) {
            throw new IllegalArgumentException("A prateleira é obrigatória");
        }

        return localizacaoRepository.save(localizacao);
    }

    public Localizacao buscarPorId(Long id) {
        return localizacaoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Localização não encontrada"));
    }

    public void remover(Long id) {
        if (!localizacaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Localização não encontrada");
        }

        localizacaoRepository.deleteById(id);
    }
}