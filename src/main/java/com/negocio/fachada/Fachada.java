package com.negocio.fachada;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Bibliotecario;
import com.biblioteca.model.Exemplar;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Localizacao;
import com.biblioteca.model.Multa;
import com.biblioteca.model.Pessoa;
import com.biblioteca.exception.CpfInvalidoException;
import com.biblioteca.exception.LeitorNaoEncontradoException;
import com.biblioteca.exception.MultaJaPagaException;
import com.negocio.cadastro.LeitorCadastro;
import com.negocio.cadastro.MultaCadastro;
import com.negocio.cadastro.PessoaCadastro;
import com.negocio.exception.ExemplarInvalidoException;
import com.negocio.exception.ExemplarNaoEncontradoException;
import com.negocio.exception.FachadaException;
import com.negocio.exception.LocalizacaoInvalidaException;
import com.negocio.exception.LocalizacaoNaoEncontradaException;
import com.negocio.service.BibliotecaService;
import com.negocio.service.BibliotecarioService;
import com.negocio.service.ExemplarService;
import com.negocio.service.LocalizacaoService;

@Service
public class Fachada {

    private final BibliotecaService bibliotecaService;
    private final BibliotecarioService bibliotecarioService;
    private final PessoaCadastro pessoaCadastro;
    private final LeitorCadastro leitorCadastro;
    private final MultaCadastro multaCadastro;
    private final ExemplarService exemplarService;
    private final LocalizacaoService localizacaoService;

    public Fachada(
            BibliotecaService bibliotecaService,
            BibliotecarioService bibliotecarioService,
            ExemplarService exemplarService,
            LocalizacaoService localizacaoService,
            PessoaCadastro pessoaCadastro,
            LeitorCadastro leitorCadastro,
            MultaCadastro multaCadastro) {

        this.bibliotecaService = bibliotecaService;
        this.bibliotecarioService = bibliotecarioService;
        this.exemplarService = exemplarService;
        this.localizacaoService = localizacaoService;
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

    // =========================
    // EXEMPLAR
    // =========================

    public Exemplar cadastrarExemplar(Exemplar exemplar)
            throws ExemplarInvalidoException {

        if (exemplar == null) {
            throw new FachadaException(
                    "O exemplar não pode ser nulo");
        }

        if (exemplar.getLivro() == null) {
            throw new FachadaException(
                    "Não é possível cadastrar um exemplar sem livro");
        }

        if (exemplar.getLocalizacao() == null) {
            throw new FachadaException(
                    "Não é possível cadastrar um exemplar sem localização");
        }

        return exemplarService.salvar(exemplar);
    }

    public Exemplar buscarExemplar(Long id)
            throws ExemplarNaoEncontradoException {

        return exemplarService.buscarPorId(id);
    }

    public List<Exemplar> listarExemplares() {
        return exemplarService.listarTodos();
    }

    public void removerExemplar(Long id)
            throws ExemplarNaoEncontradoException {

        exemplarService.remover(id);
    }

    // =========================
    // LOCALIZACAO
    // =========================

    public Localizacao cadastrarLocalizacao(Localizacao localizacao)
            throws LocalizacaoInvalidaException {

        return localizacaoService.salvar(localizacao);
    }

    public Localizacao buscarLocalizacao(Long id)
            throws LocalizacaoNaoEncontradaException {

        return localizacaoService.buscarPorId(id);
    }

    public List<Localizacao> listarLocalizacoes() {
        return localizacaoService.listarTodas();
    }

    public void removerLocalizacao(Long id)
            throws LocalizacaoNaoEncontradaException {

        localizacaoService.remover(id);
    }
}