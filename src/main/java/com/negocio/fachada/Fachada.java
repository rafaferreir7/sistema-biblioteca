package com.negocio.fachada;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Bibliotecario;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Multa;
import com.biblioteca.model.Pessoa;
import com.biblioteca.exception.CpfInvalidoException;
import com.biblioteca.exception.LeitorNaoEncontradoException;
import com.biblioteca.exception.MultaJaPagaException;
import com.negocio.cadastro.LeitorCadastro;
import com.negocio.cadastro.MultaCadastro;
import com.negocio.cadastro.PessoaCadastro;
import com.negocio.service.BibliotecaService;
import com.negocio.service.BibliotecarioService;

@Service
public class Fachada {

    private final BibliotecaService bibliotecaService;
    private final BibliotecarioService bibliotecarioService;
    private final PessoaCadastro pessoaCadastro;
    private final LeitorCadastro leitorCadastro;
    private final MultaCadastro multaCadastro;

    public Fachada(
            BibliotecaService bibliotecaService,
            BibliotecarioService bibliotecarioService,
            PessoaCadastro pessoaCadastro,
            LeitorCadastro leitorCadastro,
            MultaCadastro multaCadastro) {

        this.bibliotecaService = bibliotecaService;
        this.bibliotecarioService = bibliotecarioService;
        this.pessoaCadastro = pessoaCadastro;
        this.leitorCadastro = leitorCadastro;
        this.multaCadastro = multaCadastro;
    }

    // =========================
    // BIBLIOTECA
    // =========================

    public Biblioteca cadastrarBiblioteca(Biblioteca biblioteca) {
        return bibliotecaService.cadastrar(biblioteca);
    }

    public Biblioteca buscarBiblioteca(Long id) {
        return bibliotecaService.buscarPorId(id);
    }

    public List<Biblioteca> listarBibliotecas() {
        return bibliotecaService.listarTodas();
    }

    public Biblioteca atualizarBiblioteca(Long id, Biblioteca biblioteca) {
        return bibliotecaService.atualizar(id, biblioteca);
    }

    public void removerBiblioteca(Long id) {
        bibliotecaService.remover(id);
    }

    // =========================
    // BIBLIOTECARIO
    // =========================

    public Bibliotecario cadastrarBibliotecario(Bibliotecario bibliotecario) {
        return bibliotecarioService.cadastrar(bibliotecario);
    }

    public Bibliotecario buscarBibliotecario(Long id) {
        return bibliotecarioService.buscarPorId(id);
    }

    public List<Bibliotecario> listarBibliotecarios() {
        return bibliotecarioService.listarTodos();
    }

    public Bibliotecario atualizarBibliotecario(
            Long id,
            Bibliotecario bibliotecario) {

        return bibliotecarioService.atualizar(id, bibliotecario);
    }

    public void removerBibliotecario(Long id) {
        bibliotecarioService.remover(id);
    }

    // =========================
    // PESSOA
    // =========================

    public List<Pessoa> listarPessoas() {
        return pessoaCadastro.listarTodas();
    }

    public Optional<Pessoa> buscarPessoa(Long id) {
        return pessoaCadastro.buscarPorId(id);
    }

    public void removerPessoa(Long id) {
        pessoaCadastro.excluirPessoa(id);
    }

    // =========================
    // LEITOR
    // =========================

    public Leitor cadastrarLeitor(Leitor leitor)
            throws CpfInvalidoException {

    	return leitorCadastro.cadastrarLeitor(leitor);
    }

    public Leitor buscarLeitor(Long id)
            throws LeitorNaoEncontradoException {

        return leitorCadastro.buscarPorId(id);
    }

    public List<Leitor> listarLeitores() {
        return leitorCadastro.listarTodos();
    }

    public void removerLeitor(Long id)
            throws LeitorNaoEncontradoException {

    	leitorCadastro.excluirLeitor(id);
    }

    // =========================
    // MULTA
    // =========================

    public Multa criarMulta(Multa multa) {
        return multaCadastro.criarMulta(multa);
    }

    public Multa pagarMulta(Long id)
            throws MultaJaPagaException {

        return multaCadastro.pagarMulta(id);
    }

    public List<Multa> listarMultas() {
        return multaCadastro.listarTodas();
    }

    public void removerMulta(Long id) {
        multaCadastro.excluirMulta(id);
    }
}