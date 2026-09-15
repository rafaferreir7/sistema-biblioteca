package com.biblioteca.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.negocio.exception.ExemplarIndisponivelException;

public class ExemplarTest {

    @Test
    void deveTornarExemplarIndisponivel() throws ExemplarIndisponivelException {

        Exemplar exemplar = new Exemplar();
        exemplar.setDisponivel(true);

        exemplar.tornarIndisponivel();

        assertFalse(exemplar.isDisponivel());
    }

    @Test
    void deveLancarExcecaoQuandoExemplarJaEstiverIndisponivel() {

        Exemplar exemplar = new Exemplar();
        exemplar.setDisponivel(false);

        assertThrows(
                ExemplarIndisponivelException.class,
                () -> exemplar.tornarIndisponivel()
        );
    }
}