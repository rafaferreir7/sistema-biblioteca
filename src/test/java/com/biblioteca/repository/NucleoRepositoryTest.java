package com.biblioteca.repository;

import com.biblioteca.model.Leitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NucleoRepositoryTest {

    @Autowired
    private LeitorRepository leitorRepo;

    @Test
    public void testSalvarLeitor() {
        Leitor leitor = new Leitor();
        leitor.setNome("Rafaela");
        leitor.setCpf("12345678900");
        
        Leitor leitorSalvo = leitorRepo.save(leitor);

        assertNotNull(leitorSalvo.getId());
    }
}