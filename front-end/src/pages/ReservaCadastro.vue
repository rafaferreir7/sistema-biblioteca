<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { api } from '../api/api.js';

const router = useRouter();
const { t } = useI18n();
const reserva = ref({ leitorId: '', livroId: '' });

const salvarReserva = async () => {
  try {
    await api.post('/reservas', reserva.value);
    alert(t('reserva.sucesso'));
    router.push('/reservas');
  } catch (erro) {
    alert(t('reserva.erroSalvar'));
    console.error("Erro na API:", erro);
  }
};
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('reserva.cadastrarTitulo') }}</h2>

        <form @submit.prevent="salvarReserva">
          <div class="mb-3">
            <label class="form-label">{{ t('reserva.idLeitor') }}</label>
            <input type="number" v-model="reserva.leitorId" required class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('reserva.idLivro') }}</label>
            <input type="number" v-model="reserva.livroId" required class="form-control" />
          </div>

          <button type="submit" class="btn btn-success w-100">{{ t('reserva.salvar') }}</button>
        </form>
      </div>
    </div>
  </div>
</template>