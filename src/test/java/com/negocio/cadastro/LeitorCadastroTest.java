package com.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.model.Leitor;
import com.biblioteca.repository.LeitorRepository;

@ExtendWith(MockitoExtension.class)
public class LeitorCadastroTest {

    @Mock
    private LeitorRepository leitorRepository;

    @InjectMocks
    private LeitorCadastro leitorCadastro;

    @Test
    public void deveCadastrarLeitorComSucesso() throws Exception {
        Leitor leitor = new Leitor();
        leitor.setNome("Eduardo Guilherme");
        leitor.setCpf("12345678900");

        when(leitorRepository.save(any(Leitor.class))).thenReturn(leitor);

        Leitor salvo = leitorCadastro.cadastrarLeitor(leitor);

        assertNotNull(salvo);
        assertEquals("Eduardo Guilherme", salvo.getNome());
    }
}