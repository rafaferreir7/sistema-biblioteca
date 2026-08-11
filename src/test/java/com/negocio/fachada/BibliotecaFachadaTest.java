package com.negocio.fachada;

import com.biblioteca.model.Emprestimo;
import com.negocio.cadastro.EmprestimoCadastro;
import com.negocio.exception.FachadaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BibliotecaFachadaTest {

    @Mock
    private EmprestimoCadastro emprestimoCadastro;

    @InjectMocks
    private BibliotecaFachadaImpl bibliotecaFachada;

    @Test
    public void deveLancarFachadaExceptionSeLeitorOuLivroFaltaremNoEmprestimo() {
        
        Emprestimo emprestimoIncompleto = new Emprestimo();
        assertThrows(FachadaException.class, () -> {
            bibliotecaFachada.realizarEmprestimoComplexo(emprestimoIncompleto);
        });
    }
}