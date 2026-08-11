package com.negocio.cadastro;

import java.util.List;

import com.biblioteca.model.Reserva;

public interface ReservaCadastro {
    Reserva criarReserva(Reserva reserva);
    Reserva buscarPorId(Long id);
    List<Reserva> listarTodas();
}