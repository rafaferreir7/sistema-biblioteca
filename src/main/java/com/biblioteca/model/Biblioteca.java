package com.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
@NotBlank(message = "Nome é obrigatório")
private String nome;

@NotBlank(message = "Email é obrigatório")
@Email(message = "Email inválido")
private String email;

@NotBlank(message = "Telefone é obrigatório")
private String telefone;
    @OneToMany
    private List<Livro> livros = new ArrayList<>();

    @OneToMany
    private List<Pessoa> usuarios = new ArrayList<>();

    public Biblioteca() {
    }

    public Biblioteca(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Pessoa> getUsuarios() {
        return usuarios;
    }


    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Pessoa pessoa) {
        usuarios.add(pessoa);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void removerUsuario(Pessoa pessoa) {
        usuarios.remove(pessoa);
    }
}
