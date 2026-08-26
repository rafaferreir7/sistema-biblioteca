<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()
const categorias = ref([])
const carregando = ref(true)

const buscarCategorias = async () => {
  carregando.value = true
  try {
    const resposta = await api.get('/api/categorias')
    categorias.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar categorias:", erro)
  } finally {
    carregando.value = false
  }
}

onMounted(() => {
  buscarCategorias()
})
</script>

<template>
  <div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>{{ t('categoria.listaTitulo') }}</h2>
      <div class="d-flex gap-2">
        <button class="btn btn-outline-secondary" @click="buscarCategorias">
          <i class="bi bi-arrow-clockwise"></i> {{ t('geral.atualizar') }}
        </button>
        <button class="btn btn-primary" @click="router.push('/categorias/novo')">
          <i class="bi bi-plus-lg"></i> {{ t('categoria.novo') }}
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
            <th>{{ t('categoria.nome') }}</th>
            <th>{{ t('categoria.descricao') }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="categoria in categorias" :key="categoria.id">
            <td>{{ categoria.id }}</td>
            <td>{{ categoria.nome }}</td>
            <td>{{ categoria.descricao }}</td>
          </tr>
          <tr v-if="categorias.length === 0">
            <td colspan="3" class="text-center text-muted">{{ t('categoria.nenhum') }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>