<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { api } from '../api/api.js';

const router = useRouter();
const multas = ref([]);

async function carregarMultas() {
  try {
    const res = await api.get('/multas');
    multas.value = res.data;
  } catch (error) {
    console.error('Erro ao buscar multas:', error);
  }
}

async function pagarMulta(id) {
  if (confirm(`Deseja confirmar o pagamento da multa #${id}?`)) {
    try {
      await api.put(`/multas/${id}/pagar`);
      alert('Multa paga com sucesso!');
      carregarMultas();
    } catch (error) {
      alert('Erro ao processar pagamento.');
    }
  }
}

onMounted(() => {
  carregarMultas();
});
</script>

<template>
  <div class="container">
    <div class="header">
      <h2>Lista de Multas</h2>
      <button @click="router.push('/multas/novo')">+ Nova Multa</button>
    </div>

    <table border="1" width="100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Motivo</th>
          <th>Valor</th>
          <th>Status</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in multas" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.motivo }}</td>
          <td>R$ {{ item.valor.toFixed(2) }}</td>
          <td>
            <span :class="item.paga ? 'pago' : 'pendente'">
              {{ item.paga ? 'Paga' : 'Pendente' }}
            </span>
          </td>
          <td>
            <button v-if="!item.paga" class="btn-pagar" @click="pagarMulta(item.id)">
              Pagar
            </button>
            <span v-else>Quitada</span>
          </td>
        </tr>
        <tr v-if="multas.length === 0">
          <td colspan="5" style="text-align: center;">Nenhuma multa cadastrada.</td>
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
.pago { color: green; font-weight: bold; }
.pendente { color: red; font-weight: bold; }
.btn-pagar { background: #ffc107; color: black; border: none; border-radius: 4px; padding: 4px 8px; cursor: pointer; }
button { padding: 8px 12px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
</style>