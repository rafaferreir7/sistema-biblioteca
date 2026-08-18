package com.negocio.fachada;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Categoria;
import com.negocio.exception.FachadaException;
import com.negocio.exception.LivroInvalidoException;
import com.negocio.exception.LivroNaoEncontradoException;
import com.negocio.exception.AutorInvalidoException;
import com.negocio.exception.AutorNaoEncontradoException;
import com.negocio.exception.CategoriaInvalidaException;
import com.negocio.exception.CategoriaNaoEncontradaException;
import com.negocio.service.LivroService;
import com.negocio.service.AutorService;
import com.negocio.service.CategoriaService;

@Service
public class BibliotecaFachadaImpl implements BibliotecaFachada {

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public Emprestimo realizarEmprestimoComplexo(Emprestimo emprestimo) {
        if (emprestimo.getLeitor() == null || emprestimo.getLivro() == null) {
            throw new FachadaException("Leitor ou Livro faltantes no empréstimo");
        }
        return emprestimo;
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return null;
    }

    @Override
    public Reserva criarReserva(Reserva reserva) {
        return null;
    }

    @Override
    public Livro cadastrarLivro(Livro livro) throws LivroInvalidoException {
        return livroService.salvarLivro(livro);
    }

    @Override
    public Livro buscarLivro(Long id) throws LivroNaoEncontradoException {
        return livroService.buscarLivro(id);
    }

    @Override
    public List<Livro> listarLivros() {
        return livroService.listarTodosLivros();
    }

    @Override
    public Livro atualizarLivro(Livro livro) throws LivroInvalidoException, LivroNaoEncontradoException {
        return livroService.atualizarLivro(livro);
    }

    @Override
    public void removerLivro(Long id) throws LivroNaoEncontradoException {
        livroService.removerLivro(id);
    }

    @Override
    public Autor cadastrarAutor(Autor autor) throws AutorInvalidoException {
        return autorService.salvarAutor(autor);
    }

    @Override
    public Autor buscarAutor(Long id) throws AutorNaoEncontradoException {
        return autorService.buscarAutor(id);
    }

    @Override
    public List<Autor> listarAutores() {
        return autorService.listarTodosAutores();
    }

    @Override
    public Autor atualizarAutor(Autor autor) throws AutorInvalidoException, AutorNaoEncontradoException {
        return autorService.atualizarAutor(autor);
    }

    @Override
    public void removerAutor(Long id) throws AutorNaoEncontradoException {
        autorService.removerAutor(id);
    }

    @Override
    public Categoria cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        return categoriaService.salvarCategoria(categoria);
    }

    @Override
    public Categoria buscarCategoria(Long id) throws CategoriaNaoEncontradaException {
        return categoriaService.buscarCategoria(id);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaService.listarTodasCategorias();
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria) throws CategoriaInvalidaException, CategoriaNaoEncontradaException {
        return categoriaService.atualizarCategoria(categoria);
    }

    @Override
    public void removerCategoria(Long id) throws CategoriaNaoEncontradaException {
        categoriaService.removerCategoria(id);
    }
}