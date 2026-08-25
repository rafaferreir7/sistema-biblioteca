<template>
<<<<<<< HEAD
  <nav>
    <strong>Sistema Biblioteca</strong>

    <router-link to="/emprestimos">
  Empréstimos
</router-link>

<router-link to="/emprestimos/novo">
  Novo Empréstimo
</router-link>

    <router-link to="/bibliotecarios">
  Bibliotecários
</router-link>

<router-link to="/bibliotecarios/novo">
  Novo Bibliotecário
</router-link>
    <router-link to="/bibliotecas">
  Bibliotecas
</router-link>

<router-link to="/bibliotecas/novo">
  Nova Biblioteca
</router-link>
    <router-link to="/reservas">
      Listar Reservas
    </router-link>

    <router-link to="/reservas/novo">
      Nova Reserva
    </router-link>
=======
  <nav style="display: flex; gap: 15px; padding: 20px; background: #eee; flex-wrap: wrap;">
    <b>Sistema Biblioteca</b>
    <router-link to="/reservas">Listar Reservas</router-link>
    <router-link to="/reservas/novo">Nova Reserva</router-link>
    <span>|</span>
    <router-link to="/leitores">Listar Leitores</router-link>
    <router-link to="/leitores/novo">Novo Leitor</router-link>
    <span>|</span>
    <router-link to="/multas">Listar Multas</router-link>
    <router-link to="/multas/novo">Nova Multa</router-link>
    <span>|</span>
    <router-link to="/livros">Listar Livros</router-link>
    <router-link to="/livros/novo">Novo Livro</router-link>
    <span>|</span>
    <router-link to="/autores">Listar Autores</router-link>
    <router-link to="/autores/novo">Novo Autor</router-link>
    <span>|</span>
    <router-link to="/categorias">Listar Categorias</router-link>
    <router-link to="/categorias/novo">Nova Categoria</router-link>
>>>>>>> fb71eedcde9b06bee2ae21606b4b2c144ccf7b36
  </nav>
</template>