package com.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.exception.CpfInvalidoException;
import com.biblioteca.exception.LeitorNaoEncontradoException;
import com.biblioteca.model.Leitor;
import com.biblioteca.repository.LeitorRepository;

@Service
public class LeitorCadastro implements LeitorCadastroInterface {

    @Autowired
    private LeitorRepository leitorRepository;

    public Leitor cadastrarLeitor(Leitor leitor) throws CpfInvalidoException {
        if (leitor.getCpf() == null || leitor.getCpf().trim().isEmpty()) {
            throw new CpfInvalidoException(leitor.getCpf());
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

    public void excluirLeitor(Long id) throws LeitorNaoEncontradoException {
        Leitor leitor = buscarPorId(id);
        leitorRepository.delete(leitor);
    }
}