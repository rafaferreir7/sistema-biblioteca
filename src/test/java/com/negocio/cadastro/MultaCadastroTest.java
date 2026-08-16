package com.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.model.Multa;
import com.biblioteca.repository.MultaRepository;

@ExtendWith(MockitoExtension.class)
public class MultaCadastroTest {

    @Mock
    private MultaRepository multaRepository;

    @InjectMocks
    private MultaCadastro multaCadastro;

    @Test
    public void deveCriarMultaComSucesso() {
        Multa multa = new Multa();
        multa.setValor(15.0);

        when(multaRepository.save(any(Multa.class))).thenReturn(multa);

        Multa criada = multaCadastro.criarMulta(multa);

        assertNotNull(criada);
        assertTrue(!criada.isPaga());
    }
}