<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const leitor = ref({
  nome: '',
  cpf: '',
  email: '',
  telefone: '',
  ativo: true
});
const mensagemErro = ref('');

async function salvar() {
  mensagemErro.value = '';
  try {
    await api.post('/leitores', leitor.value);
    alert(t('leitor.sucesso'));
    router.push('/leitores');
  } catch (error) {
    mensagemErro.value = error.response?.data?.message || error.response?.data || t('leitor.erro');
  }
}
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('leitor.cadastrarTitulo') }}</h2>
        <div v-if="mensagemErro" class="alert alert-danger">{{ mensagemErro }}</div>

        <form @submit.prevent="salvar">
          <div class="mb-3">
            <label class="form-label">{{ t('leitor.nome') }}</label>
            <input v-model="leitor.nome" required class="form-control" placeholder="Nome completo" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('leitor.cpf') }}</label>
            <input v-model="leitor.cpf" required class="form-control" placeholder="000.000.000-00" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('leitor.email') }}</label>
            <input type="email" v-model="leitor.email" required class="form-control" placeholder="exemplo@email.com" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('leitor.telefone') }}</label>
            <input v-model="leitor.telefone" required class="form-control" placeholder="87981415180" />
          </div>

          <button type="submit" class="btn btn-success w-100">{{ t('leitor.salvar') }}</button>
        </form>
      </div>
    </div>
  </div>
</template>