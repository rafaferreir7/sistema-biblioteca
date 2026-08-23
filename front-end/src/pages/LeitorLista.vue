<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { api } from '../api/api.js';

const router = useRouter();
const leitores = ref([]);

async function carregarLeitores() {
  try {
    const res = await api.get('/leitores');
    leitores.value = res.data;
  } catch (error) {
    console.error('Erro ao buscar leitores:', error);
  }
}

onMounted(() => {
  carregarLeitores();
});
</script>

<template>
  <div class="container">
    <div class="header">
      <h2>Lista de Leitores</h2>
      <button @click="router.push('/leitores/novo')">+ Novo Leitor</button>
    </div>

    <table border="1" width="100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>CPF</th>
          <th>E-mail</th>
          <th>Telefone</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in leitores" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.nome }}</td>
          <td>{{ item.cpf }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.telefone }}</td>
        </tr>
        <tr v-if="leitores.length === 0">
          <td colspan="5" style="text-align: center;">Nenhum leitor cadastrado.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.container { max-width: 800px; margin: 20px auto; padding: 20px; }
.header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
table { border-collapse: collapse; text-align: left; }
th, td { padding: 8px; }
button { padding: 8px 12px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
</style>