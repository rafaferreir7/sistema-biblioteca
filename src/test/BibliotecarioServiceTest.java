package com.negocio.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.model.Bibliotecario;
import com.biblioteca.repository.BibliotecarioRepository;
import com.negocio.excecao.BibliotecarioNaoEncontradoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BibliotecarioServiceTest {

    @Mock
    private BibliotecarioRepository bibliotecarioRepository;

    @InjectMocks
    private BibliotecarioService bibliotecarioService;

    @Test
    void deveCadastrarBibliotecario() {

        Bibliotecario bibliotecario =
                new Bibliotecario();

        bibliotecario.setNome("João");
        bibliotecario.setEmail("joao@email.com");
        bibliotecario.setTelefone("81999999999");
        bibliotecario.setCargo("Bibliotecário");
        bibliotecario.setDataAdmissao(
                LocalDate.of(2025, 1, 10)
        );

        when(bibliotecarioRepository.save(bibliotecario))
                .thenReturn(bibliotecario);

        Bibliotecario resultado =
                bibliotecarioService.cadastrar(bibliotecario);

        assertNotNull(resultado);
        assertEquals(
                "João",
                resultado.getNome()
        );
        assertEquals(
                "Bibliotecário",
                resultado.getCargo()
        );

        verify(bibliotecarioRepository)
                .save(bibliotecario);
    }

    @Test
    void naoDeveCadastrarSemCargo() {

        Bibliotecario bibliotecario =
                new Bibliotecario();

        bibliotecario.setNome("João");
        bibliotecario.setEmail("joao@email.com");
        bibliotecario.setTelefone("81999999999");
        bibliotecario.setDataAdmissao(
                LocalDate.of(2025, 1, 10)
        );

        assertThrows(
                RuntimeException.class,
                () -> bibliotecarioService
                        .cadastrar(bibliotecario)
        );

        verify(
                bibliotecarioRepository,
                never()
        ).save(any());
    }

    @Test
    void deveBuscarBibliotecario() {

        Bibliotecario bibliotecario =
                new Bibliotecario();

        bibliotecario.setNome("João");
        bibliotecario.setCargo("Bibliotecário");

        when(bibliotecarioRepository.findById(1L))
                .thenReturn(Optional.of(bibliotecario));

        Bibliotecario resultado =
                bibliotecarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(
                "João",
                resultado.getNome()
        );

        verify(bibliotecarioRepository)
                .findById(1L);
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrar() {

        when(bibliotecarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                BibliotecarioNaoEncontradoException.class,
                () -> bibliotecarioService.buscarPorId(1L)
        );
    }

    @Test
    void deveRemoverBibliotecario() {

        Bibliotecario bibliotecario =
                new Bibliotecario();

        bibliotecario.setNome("João");
        bibliotecario.setCargo("Bibliotecário");

        when(bibliotecarioRepository.findById(1L))
                .thenReturn(Optional.of(bibliotecario));

        bibliotecarioService.remover(1L);

        verify(bibliotecarioRepository)
                .delete(bibliotecario);
    }
}