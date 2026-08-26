<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()
const autores = ref([])
const carregando = ref(true)

const buscarAutores = async () => {
  carregando.value = true
  try {
    const resposta = await api.get('/api/autores')
    autores.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar autores:", erro)
  } finally {
    carregando.value = false
  }
}

onMounted(() => {
  buscarAutores()
})
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('autor.listaTitulo') }}</h2>
      <div class="d-flex gap-2">
        <button class="btn btn-outline-secondary" @click="buscarAutores">
          <i class="bi bi-arrow-clockwise"></i> {{ t('geral.atualizar') }}
        </button>
        <button class="btn btn-primary" @click="router.push('/autores/novo')">
          <i class="bi bi-plus-lg"></i> {{ t('autor.novo') }}
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
            <th>ID</th>
            <th>{{ t('autor.nome') }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="autor in autores" :key="autor.id">
            <td>{{ autor.id }}</td>
            <td>{{ autor.nome }}</td>
          </tr>
          <tr v-if="autores.length === 0">
            <td colspan="2" class="text-center text-muted">{{ t('autor.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>