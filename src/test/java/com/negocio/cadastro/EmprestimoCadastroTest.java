package com.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;

@ExtendWith(MockitoExtension.class)
public class EmprestimoCadastroTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private EmprestimoCadastroImpl emprestimoCadastro;

    @Test
    public void deveLancarExcecaoAoRealizarEmprestimoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            emprestimoCadastro.realizarEmprestimo(null);
        });

        // Verifica que o método save do banco nunca foi chamado
        verify(emprestimoRepository, never()).save(any(Emprestimo.class));
    }
}