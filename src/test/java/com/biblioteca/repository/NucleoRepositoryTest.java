package com.biblioteca.repository;

import com.biblioteca.model.Bibliotecario;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NucleoRepositoryTest {

    @Autowired
    private LeitorRepository leitorRepo;

    @Autowired
    private BibliotecarioRepository bibliotecarioRepo;

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private EmprestimoRepository emprestimoRepo;

    @Test
    public void testSalvarLeitor() {
        Leitor leitor = new Leitor();
        leitor.setNome("Rafaela");
        leitor.setCpf("12345678900");
        
        Leitor leitorSalvo = leitorRepo.save(leitor);
        assertNotNull(leitorSalvo.getId());
    }

    @Test
    public void testSalvarBibliotecario() {
        Bibliotecario bibliotecario = new Bibliotecario();
        bibliotecario.setNome("Marcos");
        bibliotecario.setCargo("Gerente Geral");
        
        Bibliotecario salvo = bibliotecarioRepo.save(bibliotecario);
        assertNotNull(salvo.getId());
    }

    @Test
    public void testSalvarLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Java para Iniciantes");
        livro.setIsbn("999-888-777");
        
        Livro salvo = livroRepo.save(livro);
        assertNotNull(salvo.getId());
    }

    @Test
    public void testSalvarEmprestimo() {
        // Para testar o empréstimo, precisamos salvar um leitor e um livro antes no banco
        Leitor leitor = new Leitor();
        leitor.setNome("Ana Leitora");
        leitor = leitorRepo.save(leitor);

        Livro livro = new Livro();
        livro.setTitulo("Spring Boot Avançado");
        livro = livroRepo.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setLeitor(leitor);
        emprestimo.setLivro(livro);
        
        Emprestimo salvo = emprestimoRepo.save(emprestimo);
        assertNotNull(salvo.getId());
    }
}