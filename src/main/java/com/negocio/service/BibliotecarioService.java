package com.negocio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.model.Bibliotecario;
import com.biblioteca.repository.BibliotecarioRepository;
import com.negocio.exception.BibliotecarioInvalidoException;
import com.negocio.exception.BibliotecarioNaoEncontradoException;

@Service
public class BibliotecarioService {

    private final BibliotecarioRepository bibliotecarioRepository;

    public BibliotecarioService(
            BibliotecarioRepository bibliotecarioRepository) {

        this.bibliotecarioRepository = bibliotecarioRepository;
    }

    public Bibliotecario cadastrar(Bibliotecario bibliotecario) {

        validar(bibliotecario);

        return bibliotecarioRepository.save(bibliotecario);
    }

    public Bibliotecario buscarPorId(Long id) {

        return bibliotecarioRepository.findById(id)
                .orElseThrow(() ->
                    new BibliotecarioNaoEncontradoException(id));
    }

    public List<Bibliotecario> listarTodos() {

        return bibliotecarioRepository.findAll();
    }

    public Bibliotecario atualizar(
            Long id,
            Bibliotecario bibliotecario) {

        Bibliotecario existente = buscarPorId(id);

        validar(bibliotecario);

        existente.setNome(bibliotecario.getNome());
        existente.setEmail(bibliotecario.getEmail());
        existente.setTelefone(bibliotecario.getTelefone());
        existente.setCargo(bibliotecario.getCargo());
        existente.setDataAdmissao(
                bibliotecario.getDataAdmissao());

        return bibliotecarioRepository.save(existente);
    }

    public void remover(Long id) {

        Bibliotecario bibliotecario = buscarPorId(id);

        bibliotecarioRepository.delete(bibliotecario);
    }

    private void validar(Bibliotecario bibliotecario) {

        if (bibliotecario == null) {
            throw new BibliotecarioInvalidoException(
                    "Bibliotecário não pode ser nulo");
        }

        if (bibliotecario.getNome() == null ||
                bibliotecario.getNome().isBlank()) {

            throw new BibliotecarioInvalidoException(
                    "Nome do bibliotecário é obrigatório");
        }

        if (bibliotecario.getEmail() == null ||
                bibliotecario.getEmail().isBlank()) {

            throw new BibliotecarioInvalidoException(
                    "Email do bibliotecário é obrigatório");
        }

        if (bibliotecario.getTelefone() == null ||
                bibliotecario.getTelefone().isBlank()) {

            throw new BibliotecarioInvalidoException(
                    "Telefone do bibliotecário é obrigatório");
        }

        if (bibliotecario.getCargo() == null ||
                bibliotecario.getCargo().isBlank()) {

            throw new BibliotecarioInvalidoException(
                    "Cargo do bibliotecário é obrigatório");
        }

        if (bibliotecario.getDataAdmissao() == null) {
            throw new BibliotecarioInvalidoException(
                    "Data de admissão é obrigatória");
        }
    }
}