<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()
const livros = ref([])
const carregando = ref(true)

const buscarLivros = async () => {
  carregando.value = true
  try {
    const resposta = await api.get('/api/livros')
    livros.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar livros:", erro)
  } finally {
    carregando.value = false
  }
}

onMounted(() => {
  buscarLivros()
})
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('livro.listaTitulo') }}</h2>
      <div class="d-flex gap-2">
        <button class="btn btn-outline-secondary" @click="buscarLivros">
          <i class="bi bi-arrow-clockwise"></i> {{ t('geral.atualizar') }}
        </button>
        <button class="btn btn-primary" @click="router.push('/livros/novo')">
          <i class="bi bi-plus-lg"></i> {{ t('livro.novo') }}
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
            <th>{{ t('livro.titulo') }}</th>
            <th>{{ t('livro.isbn') }}</th>
            <th>{{ t('livro.categoria') }}</th>
            <th>{{ t('livro.autores') }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="livro in livros" :key="livro.id">
            <td>{{ livro.id }}</td>
            <td>{{ livro.titulo }}</td>
            <td>{{ livro.isbn }}</td>
            <td>{{ livro.categoria?.nome }}</td>
            <td>{{ livro.autores?.map(a => a.nome).join(', ') }}</td>
          </tr>
          <tr v-if="livros.length === 0">
            <td colspan="5" class="text-center text-muted">{{ t('livro.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>