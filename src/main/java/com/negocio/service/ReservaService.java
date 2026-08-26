package com.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Reserva;
import com.biblioteca.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva salvarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
}