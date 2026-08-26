<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const multas = ref([]);
const carregando = ref(true);

async function carregarMultas() {
  carregando.value = true;
  try {
    const res = await api.get('/multas');
    multas.value = res.data;
  } catch (error) {
    console.error('Erro ao buscar multas:', error);
  } finally {
    carregando.value = false;
  }
}

async function pagarMulta(id) {
  if (confirm(t('multa.confirmarPagamento', { id }))) {
    try {
      await api.put(`/multas/${id}/pagar`);
      alert(t('multa.pagoSucesso'));
      carregarMultas();
    } catch (error) {
      alert(t('multa.erroPagamento'));
    }
  }
}

onMounted(() => {
  carregarMultas();
});
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('multa.listaTitulo') }}</h2>
      <button class="btn btn-primary" @click="router.push('/multas/novo')">
        <i class="bi bi-plus-lg"></i> {{ t('multa.novo') }}
      </button>
    </div>

    <div v-if="carregando" class="text-center text-muted py-4">
      {{ t('geral.carregando') }}
    </div>

    <div v-else class="table-responsive">
      <table class="table table-striped table-hover align-middle">
        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>{{ t('multa.motivo') }}</th>
            <th>{{ t('multa.valor') }}</th>
            <th>{{ t('multa.status') }}</th>
            <th>{{ t('multa.acoes') }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in multas" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.motivo }}</td>
            <td>R$ {{ item.valor.toFixed(2) }}</td>
            <td>
              <span :class="item.paga ? 'badge text-bg-success' : 'badge text-bg-danger'">
                {{ item.paga ? t('multa.paga') : t('multa.pendente') }}
              </span>
            </td>
            <td>
              <button v-if="!item.paga" class="btn btn-warning btn-sm" @click="pagarMulta(item.id)">
                {{ t('multa.pagar') }}
              </button>
              <span v-else class="text-muted">{{ t('multa.quitada') }}</span>
            </td>
          </tr>
          <tr v-if="multas.length === 0">
            <td colspan="5" class="text-center text-muted">{{ t('multa.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>