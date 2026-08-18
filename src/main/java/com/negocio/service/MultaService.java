package com.negocio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.exception.MultaJaPagaException;
import com.biblioteca.exception.MultaNaoEncontradaException;
import com.biblioteca.model.Multa;
import com.biblioteca.repository.MultaRepository;

@Service
public class MultaService {

    private final MultaRepository multaRepository;

    public MultaService(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    public Multa cadastrar(Multa multa) {

        if (multa == null) {
            throw new IllegalArgumentException(
                    "Multa não pode ser nula"
            );
        }

        if (multa.getValor() <= 0) {
            throw new IllegalArgumentException(
                    "Valor da multa deve ser maior que zero"
            );
        }

        if (multa.getMotivo() == null || multa.getMotivo().isBlank()) {
            throw new IllegalArgumentException(
                    "Motivo da multa é obrigatório"
            );
        }

        if (multa.getEmprestimo() == null) {
            throw new IllegalArgumentException(
                    "Empréstimo é obrigatório"
            );
        }

        return multaRepository.save(multa);
    }

    public Multa buscarPorId(Long id) {

        return multaRepository.findById(id)
                .orElseThrow(() ->
                        new MultaNaoEncontradaException(id)
                );
    }

    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    public Multa pagar(Long id) {

        Multa multa = buscarPorId(id);

        if (multa.isPaga()) {
            throw new MultaJaPagaException(id);
        }

        multa.pagar();

        return multaRepository.save(multa);
    }

    public void remover(Long id) {

        Multa multa = buscarPorId(id);

        multaRepository.delete(multa);
    }
}