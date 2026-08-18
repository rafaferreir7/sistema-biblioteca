package com.negocio.fachada;

import java.util.List;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Categoria;
import com.negocio.exception.LivroInvalidoException;
import com.negocio.exception.LivroNaoEncontradoException;
import com.negocio.exception.AutorInvalidoException;
import com.negocio.exception.AutorNaoEncontradoException;
import com.negocio.exception.CategoriaInvalidaException;
import com.negocio.exception.CategoriaNaoEncontradaException;

public interface BibliotecaFachada {
    Emprestimo realizarEmprestimoComplexo(Emprestimo emprestimo);
    List<Emprestimo> listarEmprestimos();
    Reserva criarReserva(Reserva reserva);

    Livro cadastrarLivro(Livro livro) throws LivroInvalidoException;
    Livro buscarLivro(Long id) throws LivroNaoEncontradoException;
    List<Livro> listarLivros();
    Livro atualizarLivro(Livro livro) throws LivroInvalidoException, LivroNaoEncontradoException;
    void removerLivro(Long id) throws LivroNaoEncontradoException;

    Autor cadastrarAutor(Autor autor) throws AutorInvalidoException;
    Autor buscarAutor(Long id) throws AutorNaoEncontradoException;
    List<Autor> listarAutores();
    Autor atualizarAutor(Autor autor) throws AutorInvalidoException, AutorNaoEncontradoException;
    void removerAutor(Long id) throws AutorNaoEncontradoException;

    Categoria cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException;
    Categoria buscarCategoria(Long id) throws CategoriaNaoEncontradaException;
    List<Categoria> listarCategorias();
    Categoria atualizarCategoria(Categoria categoria) throws CategoriaInvalidaException, CategoriaNaoEncontradaException;
    void removerCategoria(Long id) throws CategoriaNaoEncontradaException;
}