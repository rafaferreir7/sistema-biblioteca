package com.negocio.service;

import com.biblioteca.exception.BibliotecaNaoEncontradaException;
import com.biblioteca.model.Biblioteca;
import com.biblioteca.repository.BibliotecaRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BibliotecaServiceTest {

    @Mock
    private BibliotecaRepository bibliotecaRepository;

    @InjectMocks
    private BibliotecaService bibliotecaService;

    @Test
    void deveCadastrarBiblioteca() {

        Biblioteca biblioteca = new Biblioteca(
                "Biblioteca Central",
                "biblioteca@email.com",
                "81999999999"
        );

        when(bibliotecaRepository.save(biblioteca))
                .thenReturn(biblioteca);

        Biblioteca resultado = bibliotecaService.cadastrar(biblioteca);

        assertNotNull(resultado);
        assertEquals("Biblioteca Central", resultado.getNome());

        verify(bibliotecaRepository).save(biblioteca);
    }

    @Test
    void naoDeveCadastrarBibliotecaSemNome() {

        Biblioteca biblioteca = new Biblioteca(
                "",
                "biblioteca@email.com",
                "81999999999"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> bibliotecaService.cadastrar(biblioteca)
        );

        verify(bibliotecaRepository, never()).save(any());
    }

    @Test
    void deveBuscarBibliotecaPorId() {

        Biblioteca biblioteca = new Biblioteca(
                "Biblioteca Central",
                "biblioteca@email.com",
                "81999999999"
        );

        when(bibliotecaRepository.findById(1L))
                .thenReturn(Optional.of(biblioteca));

        Biblioteca resultado = bibliotecaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Biblioteca Central", resultado.getNome());

        verify(bibliotecaRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoBibliotecaNaoExistir() {

        when(bibliotecaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                BibliotecaNaoEncontradaException.class,
                () -> bibliotecaService.buscarPorId(1L)
        );

        verify(bibliotecaRepository).findById(1L);
    }

    @Test
    void deveRemoverBiblioteca() {

        Biblioteca biblioteca = new Biblioteca(
                "Biblioteca Central",
                "biblioteca@email.com",
                "81999999999"
        );

        when(bibliotecaRepository.findById(1L))
                .thenReturn(Optional.of(biblioteca));

        bibliotecaService.remover(1L);

        verify(bibliotecaRepository).delete(biblioteca);
    }
}
