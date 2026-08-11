package com.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Reserva;
import com.biblioteca.repository.ReservaRepository;
import com.negocio.exception.ReservaNaoEncontradaException;

@Service
public class ReservaCadastroImpl implements ReservaCadastro {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva criarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("A reserva não pode ser nula.");
        }
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException("Reserva não encontrada para o ID: " + id));
    }

    @Override
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }
}