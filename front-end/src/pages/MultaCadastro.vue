<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { api } from '../api/api.js';

const router = useRouter();
const multa = ref({
  valor: '',
  motivo: '',
  emprestimoId: ''
});
const mensagemErro = ref('');

async function salvar() {
  try {
    await api.post('/multas', {
      valor: parseFloat(multa.value.valor),
      motivo: multa.value.motivo,
      emprestimoId: parseInt(multa.value.emprestimoId)
    });
    alert('Multa registrada com sucesso!');
    router.push('/multas');
  } catch (error) {
    mensagemErro.value = error.response?.data?.message || 'Erro ao registrar multa. Verifique se o backend está rodando.';
  }
}
</script>

<template>
  <div class="container">
    <h2>Registrar Multa</h2>
    <p v-if="mensagemErro" class="erro">{{ mensagemErro }}</p>

    <form @submit.prevent="salvar">
      <div class="campo">
        <label>Valor (R$):</label>
        <input type="number" step="0.01" v-model="multa.valor" required placeholder="15.00" />
      </div>

      <div class="campo">
        <label>Motivo:</label>
        <input v-model="multa.motivo" required placeholder="Ex: Atraso na devolução" />
      </div>

      <div class="campo">
        <label>ID do Empréstimo:</label>
        <input type="number" v-model="multa.emprestimoId" required placeholder="1" />
      </div>

      <button type="submit">Salvar Multa</button>
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