package com.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Pessoa;
import com.biblioteca.repository.PessoaRepository;

@Service
public class PessoaCadastro implements PessoaCadastroInterface {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}