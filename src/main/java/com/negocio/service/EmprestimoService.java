package com.negocio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Livro;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LeitorRepository;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.dto.EmprestimoRequestDTO;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            LivroRepository livroRepository,
            LeitorRepository leitorRepository) {

        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
    }

    public Emprestimo cadastrar(EmprestimoRequestDTO dto) {

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        Leitor leitor = leitorRepository.findById(dto.getLeitorId())
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado."));

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setLivro(livro);
        emprestimo.setLeitor(leitor);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevista(dto.getDataDevolucaoPrevista());
        emprestimo.setDevolvido(false);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Empréstimo não encontrado."
                ));
    }

    public Emprestimo registrarDevolucao(Long id) {

        Emprestimo emprestimo = buscarPorId(id);

        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucao(LocalDate.now());

        return emprestimoRepository.save(emprestimo);
    }
}