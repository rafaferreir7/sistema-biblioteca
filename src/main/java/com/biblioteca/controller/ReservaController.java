package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.ReservaRequestDTO;
import com.biblioteca.dto.ReservaResponseDTO;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Reserva;
import com.biblioteca.repository.LeitorRepository;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.ReservaRepository;
import com.negocio.fachada.BibliotecaFachada;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final BibliotecaFachada fachada;
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;
    private final ReservaRepository reservaRepository;

    public ReservaController(
            BibliotecaFachada fachada,
            LeitorRepository leitorRepository,
            LivroRepository livroRepository,
            ReservaRepository reservaRepository) {
        this.fachada = fachada;
        this.leitorRepository = leitorRepository;
        this.livroRepository = livroRepository;
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@Valid @RequestBody ReservaRequestDTO dto) {

        Leitor leitor = leitorRepository.findById(dto.leitorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Leitor não encontrado com id " + dto.leitorId()));

        Livro livro = livroRepository.findById(dto.livroId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Livro não encontrado com id " + dto.livroId()));

        Reserva novaReserva = new Reserva();
        novaReserva.setDataReserva(dto.dataReserva());
        novaReserva.setLeitor(leitor);
        novaReserva.setLivro(livro);
        novaReserva.setAtiva(true);

        Reserva reservaSalva = fachada.criarReserva(novaReserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarReservas() {

        List<ReservaResponseDTO> reservas = reservaRepository.findAll()
                .stream()
                .map(r -> new ReservaResponseDTO(
                        r.getId(),
                        r.getLeitor().getId(),
                        r.getLivro().getId(),
                        r.getDataReserva(),
                        r.isAtiva()
                ))
                .toList();

        return ResponseEntity.ok(reservas);
    }
}