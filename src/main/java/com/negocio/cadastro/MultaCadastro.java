package com.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.exception.MultaJaPagaException;
import com.biblioteca.model.Multa;
import com.biblioteca.repository.MultaRepository;

@Service
public class MultaCadastro implements MultaCadastroInterface {

    @Autowired
    private MultaRepository multaRepository;

    public Multa criarMulta(Multa multa) {
        multa.setPaga(false);
        return multaRepository.save(multa);
    }

    public Multa pagarMulta(Long id) throws MultaJaPagaException {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa não encontrada."));

        if (multa.isPaga()) {
            throw new MultaJaPagaException(id);
        }

        multa.pagar();
        return multaRepository.save(multa);
    }

    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    public void excluirMulta(Long id) {
        multaRepository.deleteById(id);
    }
}