<script setup>
import { ref, onMounted } from 'vue';
import { api } from '../api/api.js';

const reservas = ref([]);

const buscarReservas = async () => {
  try {
    const resposta = await api.get('/reservas');
    reservas.value = resposta.data;
  } catch (erro) {
    console.error("Erro ao buscar reservas:", erro);
  }
};

// Chama a busca automaticamente quando a tela abre
onMounted(() => {
  buscarReservas();
});
</script>

<template>
  <div style="padding: 20px;">
    <h2>Lista de Reservas</h2>
    <button @click="buscarReservas" style="margin-bottom: 15px; padding: 5px 10px;">Atualizar Lista</button>
    
    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
      <thead style="background-color: #f2f2f2;">
        <tr>
          <th style="padding: 8px;">ID da Reserva</th>
          <th style="padding: 8px;">ID do Leitor</th>
          <th style="padding: 8px;">ID do Livro</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="reserva in reservas" :key="reserva.id">
          <td style="padding: 8px;">{{ reserva.id }}</td>
          <td style="padding: 8px;">{{ reserva.leitorId }}</td>
          <td style="padding: 8px;">{{ reserva.livroId }}</td>
        </tr>
      </tbody>
    </table>
    
    <p v-if="reservas.length === 0" style="color: gray;">Nenhuma reserva encontrada no banco de dados.</p>
  </div>
</template>