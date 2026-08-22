package com.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.ReservaRequestDTO;
import com.biblioteca.model.Reserva;
import com.negocio.fachada.BibliotecaFachada;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final BibliotecaFachada fachada;

    public ReservaController(BibliotecaFachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@Valid @RequestBody ReservaRequestDTO dto) {
        Reserva novaReserva = new Reserva();
        novaReserva.setDataReserva(dto.dataReserva());

        Reserva reservaSalva = fachada.criarReserva(novaReserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);
    }
}