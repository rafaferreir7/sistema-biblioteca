package com.negocio.fachada;

import java.util.List;

import org.springframework.stereotype.Service; // <--- Import importante!

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Reserva;
import com.negocio.exception.FachadaException;

@Service // <--- Essa anotação avisa o Spring que esta classe é o Bean da Fachada
public class BibliotecaFachadaImpl implements BibliotecaFachada {

    @Override
    public Emprestimo realizarEmprestimoComplexo(Emprestimo emprestimo) {
        // Regra que o teste valida: se não tiver livro ou leitor, lança a exceção
        if (emprestimo.getLeitor() == null || emprestimo.getLivro() == null) {
            throw new FachadaException("Leitor ou Livro faltantes no empréstimo");
        }
        return emprestimo;
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return null;
    }

    @Override
    public Reserva criarReserva(Reserva reserva) {
        return null;
    }
}