<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { api } from '../api/api.js';

const router = useRouter();
const leitor = ref({
  nome: '',
  cpf: '',
  email: '',
  telefone: '',
  ativo: true,
  bloqueado: false
});
const mensagemErro = ref('');

async function salvar() {
  mensagemErro.value = '';
  try {
    await api.post('/leitores', leitor.value);
    alert('Leitor cadastrado com sucesso!');
    router.push('/leitores');
  } catch (error) {
    mensagemErro.value = error.response?.data?.message || error.response?.data || 'Erro ao cadastrar leitor.';
  }
}
</script>

<template>
  <div class="container">
    <h2>Cadastrar Leitor</h2>
    <p v-if="mensagemErro" class="erro">{{ mensagemErro }}</p>

    <form @submit.prevent="salvar">
      <div class="campo">
        <label>Nome:</label>
        <input v-model="leitor.nome" required placeholder="Nome completo" />
      </div>

      <div class="campo">
        <label>CPF:</label>
        <input v-model="leitor.cpf" required placeholder="000.000.000-00" />
      </div>

      <div class="campo">
        <label>E-mail:</label>
        <input type="email" v-model="leitor.email" required placeholder="exemplo@email.com" />
      </div>

      <div class="campo">
        <label>Telefone:</label>
        <input v-model="leitor.telefone" required placeholder="87981415180" />
      </div>

      <button type="submit">Salvar Leitor</button>
    </form>
  </div>
</template>

<style scoped>
.container { max-width: 500px; margin: 20px auto; padding: 20px; }
.campo { margin-bottom: 15px; display: flex; flex-direction: column; }
.campo input { padding: 8px; margin-top: 5px; }
.erro { color: red; font-weight: bold; }
button { padding: 10px 15px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
</style>