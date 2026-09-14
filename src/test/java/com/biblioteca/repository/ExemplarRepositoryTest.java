package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.model.Autor;
import com.biblioteca.model.Categoria;
import com.biblioteca.model.Exemplar;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Localizacao;

@SpringBootTest
class ExemplarRepositoryTest {

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Test
    void testeInserirExemplar() {

        Categoria categoria = new Categoria();
        categoria.setNome("Programacao");
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

        Localizacao localizacao = new Localizacao();
        localizacao.setEstante("A1");
        localizacao.setPrateleira("P2");
        localizacao = localizacaoRepository.save(localizacao);

        Exemplar exemplar = new Exemplar();
        exemplar.setCodigoPatrimonio("EX001");
        exemplar.setDisponivel(true);
        exemplar.setLivro(livro);
        exemplar.setLocalizacao(localizacao);

        exemplar = exemplarRepository.save(exemplar);

        assertNotNull(exemplar.getId());
    }
}