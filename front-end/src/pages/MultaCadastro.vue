<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const multa = ref({
  valor: '',
  motivo: '',
  emprestimoId: ''
});
const mensagemErro = ref('');

async function salvar() {
  mensagemErro.value = '';
  try {
    await api.post('/multas', {
      valor: parseFloat(multa.value.valor),
      motivo: multa.value.motivo,
      emprestimoId: parseInt(multa.value.emprestimoId)
    });
    alert(t('multa.sucesso'));
    router.push('/multas');
  } catch (error) {
    mensagemErro.value = error.response?.data?.message || t('multa.erroSalvar');
  }
}
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('multa.cadastrarTitulo') }}</h2>
        <div v-if="mensagemErro" class="alert alert-danger">{{ mensagemErro }}</div>

        <form @submit.prevent="salvar">
          <div class="mb-3">
            <label class="form-label">{{ t('multa.valorLabel') }}</label>
            <input type="number" step="0.01" v-model="multa.valor" required class="form-control" placeholder="15.00" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('multa.motivo') }}</label>
            <input v-model="multa.motivo" required class="form-control" placeholder="Ex: Atraso na devolução" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('multa.emprestimoId') }}</label>
            <input type="number" v-model="multa.emprestimoId" required class="form-control" placeholder="1" />
          </div>

          <button type="submit" class="btn btn-success w-100">{{ t('multa.salvar') }}</button>
        </form>
      </div>
    </div>
  </div>
</template>