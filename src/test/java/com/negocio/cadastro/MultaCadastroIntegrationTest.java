package com.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.biblioteca.SistemaBibliotecaApplication;
import com.biblioteca.model.Multa;

@SpringBootTest(classes = SistemaBibliotecaApplication.class)
@ComponentScan(basePackages = {"com.biblioteca", "com.negocio"})
public class MultaCadastroIntegrationTest {

    @Autowired
    private MultaCadastro multaCadastro;

    @Test
    public void deveCriarMultaNoBanco() {
        Multa multa = new Multa();
        multa.setValor(15.0);
        multa.setPaga(false);

        Multa salva = multaCadastro.criarMulta(multa);

        assertNotNull(salva);
        assertNotNull(salva.getId());
        assertTrue(!salva.isPaga());
    }
}