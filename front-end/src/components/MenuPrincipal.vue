<template>
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
  </nav>
</template>