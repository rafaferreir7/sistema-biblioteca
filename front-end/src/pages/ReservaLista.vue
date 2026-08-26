<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const reservas = ref([]);
const carregando = ref(true);

const buscarReservas = async () => {
  carregando.value = true;
  try {
    const resposta = await api.get('/reservas');
    reservas.value = resposta.data;
  } catch (erro) {
    console.error("Erro ao buscar reservas:", erro);
  } finally {
    carregando.value = false;
  }
};

onMounted(() => {
  buscarReservas();
});
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('reserva.listaTitulo') }}</h2>
      <div class="d-flex gap-2">
        <button class="btn btn-outline-secondary" @click="buscarReservas">
          <i class="bi bi-arrow-clockwise"></i> {{ t('geral.atualizar') }}
        </button>
        <button class="btn btn-primary" @click="router.push('/reservas/novo')">
          <i class="bi bi-plus-lg"></i> {{ t('reserva.novo') }}
        </button>
      </div>
    </div>

    <div v-if="carregando" class="text-center text-muted py-4">
      {{ t('geral.carregando') }}
    </div>

    <div v-else class="table-responsive">
      <table class="table table-striped table-hover align-middle">
        <thead class="table-dark">
          <tr>
            <th>{{ t('reserva.idReserva') }}</th>
            <th>{{ t('reserva.idLeitor') }}</th>
            <th>{{ t('reserva.idLivro') }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="reserva in reservas" :key="reserva.id">
            <td>{{ reserva.id }}</td>
            <td>{{ reserva.leitorId }}</td>
            <td>{{ reserva.livroId }}</td>
          </tr>
          <tr v-if="reservas.length === 0">
            <td colspan="3" class="text-center text-muted">{{ t('reserva.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>