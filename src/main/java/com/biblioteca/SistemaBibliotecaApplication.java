package com.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.biblioteca", "com.negocio"})
public class SistemaBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaBibliotecaApplication.class, args);
    }
}