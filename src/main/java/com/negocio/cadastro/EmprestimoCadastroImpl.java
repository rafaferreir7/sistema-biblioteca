package com.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;
import com.negocio.exception.EmprestimoNaoEncontradoException;

@Service
public class EmprestimoCadastroImpl implements EmprestimoCadastro {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo == null) {
            throw new IllegalArgumentException("O empréstimo não pode ser nulo.");
        }
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException("Empréstimo não encontrado para o ID: " + id));
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }
}