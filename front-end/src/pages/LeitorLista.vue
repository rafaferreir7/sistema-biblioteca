<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const leitores = ref([]);
const carregando = ref(true);

async function carregarLeitores() {
  try {
    const res = await api.get('/leitores');
    leitores.value = res.data;
  } catch (error) {
    console.error('Erro ao buscar leitores:', error);
  } finally {
    carregando.value = false;
  }
}

onMounted(() => {
  carregarLeitores();
});
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('leitor.listaTitulo') }}</h2>
      <button class="btn btn-primary" @click="router.push('/leitores/novo')">
        <i class="bi bi-plus-lg"></i> {{ t('leitor.novo') }}
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
            <th>{{ t('leitor.nome') }}</th>
            <th>{{ t('leitor.cpf') }}</th>
            <th>{{ t('leitor.email') }}</th>
            <th>{{ t('leitor.telefone') }}</th>
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
            <td colspan="5" class="text-center text-muted">{{ t('leitor.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>