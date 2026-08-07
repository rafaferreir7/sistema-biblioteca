package com.negocio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.biblioteca.model.Livro;
import com.negocio.exception.LivroInvalidoException;

public class LivroServiceTest {

    @Test
    void testLivroSemTitulo() {

        LivroService service = new LivroService();

        Livro livro = new Livro();

        assertThrows(LivroInvalidoException.class, () -> {
            service.salvarLivro(livro);
        });
    }
    
    @Test
    void testLivroSemISBN() {

        LivroService service = new LivroService();

        Livro livro = new Livro();
        livro.setTitulo("Java");

        assertThrows(LivroInvalidoException.class, () -> {
            service.salvarLivro(livro);
        });
    }
    
    @Test
    void testQuantidadeNegativa() {

        LivroService service = new LivroService();

        Livro livro = new Livro();
        livro.setTitulo("Java");
        livro.setIsbn("123");
        livro.setQuantidade(-1);

        assertThrows(LivroInvalidoException.class, () -> {
            service.salvarLivro(livro);
        });
    }

}