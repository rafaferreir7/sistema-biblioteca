![Diagrama de Classes](./diagrama-de-classes.drawio.png)

# Sistema de Gestão de Biblioteca

> Projeto de Programação Orientada a Objetos para gerenciamento de acervo, leitores, empréstimos, reservas e multas de uma biblioteca.

---

## Sobre o Projeto

O **Sistema de Gestão de Biblioteca** é uma aplicação voltada para a automação e organização do fluxo de funcionamento de uma biblioteca. Ele permite o cadastro de usuários (Leitores e Bibliotecários), gestão de catálogo de livros (com autores e categorias), controle de empréstimos, reservas e cálculo automático de multas por atraso.

---

## Diagrama de Classes

Modelagem de classes do sistema.
```mermaid
classDiagram
    class Pessoa {
        -Long id
        -String nome
        -String cpf
        -String email
        -String telefone
        +validarCpf() boolean
        +atualizarDados() void
    }

    class Leitor {
        -String matricula
        -LocalDate dataCadastro
        -boolean ativo
        +reservarLivro(Livro livro) Reserva
        +consultarEmprestimo() List~Emprestimo~
    }

    class Bibliotecario {
        -String cargo
        -LocalDate dataAdmissao
        +cadastrarLivro(Livro livro) void
        +cadastrarUsuario(Pessoa pessoa) void
    }

    class Biblioteca {
        -Long id
        -String nome
        -String email
        -String telefone
        +getLivros() List~Livro~
        +getUsuarios() List~Pessoa~
    }

    class Autor {
        -Long id
        -String nome
        +atualizar() void
    }

    class Categoria {
        -Long id
        -String nome
        -String descricao
        +atualizar() void
    }

    class Livro {
        -Long id
        -String titulo
        -String isbn
        -int ano
        -int quantidade
        -boolean disponivel
        +emprestar() void
        +devolver() void
        +isDisponivel() boolean
    }

    class Emprestimo {
        -Long id
        -LocalDate dataEmprestimo
        -LocalDate dataPrevista
        -LocalDate dataDevolucao
        -boolean devolvido
        +registrar() void
        +renovar() void
        +devolver() void
    }

    class Reserva {
        -Long id
        -LocalDate dataReserva
        -boolean ativa
        +cancelar() void
        +ativar() void
    }

    class Multa {
        -Long id
        -double valor
        -int diasAtraso
        -boolean paga
        +calcularValor() double
        +pagar() void
    }

    Pessoa <|-- Leitor : Herança
    Pessoa <|-- Bibliotecario : Herança
    Biblioteca "1" -- "*" Pessoa : gerencia
    Biblioteca "1" -- "*" Autor : possui
    Autor "1..*" ..> "*" Livro : Escreve
    Categoria "1" -- "*" Livro : possui
    Leitor "1" -- "*" Emprestimo : realiza
    Leitor "1" -- "*" Reserva : realiza
    Livro "1" -- "*" Emprestimo : associado
    Livro "1" -- "*" Reserva : reservado
    Emprestimo "1" -- "0..1" Multa : gera