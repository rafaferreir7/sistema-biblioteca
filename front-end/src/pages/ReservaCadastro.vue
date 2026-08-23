<script setup>
import { ref } from 'vue';
import { api } from '../api/api.js';

// Variável que guarda os dados que o usuário digitar
const reserva = ref({ leitorId: '', livroId: '' });

const salvarReserva = async () => {
  try {
    // Envia os dados para o seu back-end em Java
    await api.post('/reservas', reserva.value);
    alert("Reserva cadastrada com sucesso!");
    reserva.value = { leitorId: '', livroId: '' }; // Limpa o formulário
  } catch (erro) {
    alert("Erro ao salvar. Verifique se o back-end Java está rodando.");
    console.error("Erro na API:", erro);
  }
};
</script>

<template>
  <div style="padding: 20px; max-width: 400px;">
    <h2>Cadastrar Nova Reserva</h2>
    
    <form @submit.prevent="salvarReserva" style="display: flex; flex-direction: column; gap: 15px;">
      <div>
        <label>ID do Leitor:</label>
        <input type="number" v-model="reserva.leitorId" required style="width: 100%; padding: 8px;" />
      </div>
      
      <div>
        <label>ID do Livro:</label>
        <input type="number" v-model="reserva.livroId" required style="width: 100%; padding: 8px;" />
      </div>
      
      <button type="submit" style="padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
        Salvar Reserva
      </button>
    </form>
  </div>
</template>