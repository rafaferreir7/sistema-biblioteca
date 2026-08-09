package com.negocio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.exception.CpfInvalidoException;
import com.biblioteca.exception.LeitorNaoEncontradoException;
import com.biblioteca.model.Leitor;
import com.biblioteca.repository.LeitorRepository;

@Service
public class LeitorService {

    @Autowired
    private LeitorRepository leitorRepository;

    public Leitor salvarLeitor(Leitor leitor) throws CpfInvalidoException {
        if (leitor.getCpf() == null || leitor.getCpf().trim().isEmpty()) {
            throw new CpfInvalidoException("CPF não pode ser vazio.");
        }
        return leitorRepository.save(leitor);
    }

    public Leitor buscarPorId(Long id) throws LeitorNaoEncontradoException {
        return leitorRepository.findById(id)
                .orElseThrow(() -> new LeitorNaoEncontradoException(id));
    }

    public List<Leitor> listarTodos() {
        return leitorRepository.findAll();
    }

    public void deletarLeitor(Long id) throws LeitorNaoEncontradoException {
        Leitor leitor = buscarPorId(id);
        leitorRepository.delete(leitor);
    }
}