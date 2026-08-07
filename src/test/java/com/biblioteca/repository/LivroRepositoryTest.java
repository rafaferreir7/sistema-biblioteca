package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.model.Autor;
import com.biblioteca.model.Categoria;
import com.biblioteca.model.Livro;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    void testeInserirLivro() {

        Categoria categoria = new Categoria();
        categoria.setNome("Programação");
        categoria.setDescricao("Livros de TI");
        categoria = categoriaRepository.save(categoria);

        Autor autor = new Autor();
        autor.setNome("Marco");
        autor = autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo("Java");
        livro.setIsbn("9781234567890");
        livro.setAno(2026);
        livro.setQuantidade(5);
        livro.setDisponivel(true);
        livro.setCategoria(categoria);

        List<Autor> autores = new ArrayList<>();
        autores.add(autor);
        livro.setAutores(autores);

        livro = livroRepository.save(livro);

        assertNotNull(livro.getId());
    }
}