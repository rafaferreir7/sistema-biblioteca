package com.negocio.service;

import com.biblioteca.exception.MultaNaoEncontradaException;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Multa;
import com.biblioteca.repository.MultaRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MultaServiceTest {

    @Mock
    private MultaRepository multaRepository;

    @InjectMocks
    private MultaService multaService;

    @Test
    void deveCadastrarMulta() {

        Emprestimo emprestimo = new Emprestimo();

        Multa multa = new Multa(
                10.0,
                "Atraso na devolução",
                emprestimo
        );

        when(multaRepository.save(multa))
                .thenReturn(multa);

        Multa resultado = multaService.cadastrar(multa);

        assertNotNull(resultado);
        assertEquals(10.0, resultado.getValor());
        assertEquals("Atraso na devolução", resultado.getMotivo());
        assertFalse(resultado.isPaga());

        verify(multaRepository).save(multa);
    }

    @Test
    void naoDeveCadastrarMultaComValorInvalido() {

        Emprestimo emprestimo = new Emprestimo();

        Multa multa = new Multa(
                0,
                "Atraso",
                emprestimo
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> multaService.cadastrar(multa)
        );

        verify(multaRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarMultaSemMotivo() {

        Emprestimo emprestimo = new Emprestimo();

        Multa multa = new Multa(
                10.0,
                "",
                emprestimo
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> multaService.cadastrar(multa)
        );

        verify(multaRepository, never()).save(any());
    }

    @Test
    void deveBuscarMultaPorId() {

        Emprestimo emprestimo = new Emprestimo();

        Multa multa = new Multa(
                10.0,
                "Atraso",
                emprestimo
        );

        when(multaRepository.findById(1L))
                .thenReturn(Optional.of(multa));

        Multa resultado = multaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(10.0, resultado.getValor());

        verify(multaRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoMultaNaoExistir() {

        when(multaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                MultaNaoEncontradaException.class,
                () -> multaService.buscarPorId(1L)
        );

        verify(multaRepository).findById(1L);
    }

    @Test
    void devePagarMulta() {

        Emprestimo emprestimo = new Emprestimo();

        Multa multa = new Multa(
                10.0,
                "Atraso",
                emprestimo
        );

        when(multaRepository.findById(1L))
                .thenReturn(Optional.of(multa));

        when(multaRepository.save(multa))
                .thenReturn(multa);

        Multa resultado = multaService.pagar(1L);

        assertTrue(resultado.isPaga());

        verify(multaRepository).save(multa);
    }
}