package com.negocio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.biblioteca.model.Exemplar;
import com.negocio.exception.ExemplarInvalidoException;

public class ExemplarServiceTest {

    @Test
    void testExemplarNulo() {
        ExemplarService service = new ExemplarService();

        assertThrows(ExemplarInvalidoException.class, () -> {
            service.salvar(null);
        });
    }

    @Test
    void testExemplarSemCodigoPatrimonio() {
        ExemplarService service = new ExemplarService();
        Exemplar exemplar = new Exemplar();

        assertThrows(ExemplarInvalidoException.class, () -> {
            service.salvar(exemplar);
        });
    }

    @Test
    void testExemplarSemLivro() {
        ExemplarService service = new ExemplarService();
        Exemplar exemplar = new Exemplar();
        exemplar.setCodigoPatrimonio("EX001");

        assertThrows(ExemplarInvalidoException.class, () -> {
            service.salvar(exemplar);
        });
    }
}