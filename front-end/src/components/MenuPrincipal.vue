<template>
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
  </nav>
</template>