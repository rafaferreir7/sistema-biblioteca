<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api.js'

const localizacoes = ref([])
const carregando = ref(true)

const buscarLocalizacoes = async () => {
  carregando.value = true

  try {
    const resposta = await api.get('/localizacoes')
    localizacoes.value = resposta.data
  } catch (erro) {
    console.error('Erro ao buscar localizações:', erro)
  } finally {
    carregando.value = false
  }
}

onMounted(() => {
  buscarLocalizacoes()
})
</script>

<template>
  <div class="container my-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>Localizações</h2>

      <button
        class="btn btn-outline-secondary"
        @click="buscarLocalizacoes"
      >
        Atualizar
      </button>
    </div>

    <div v-if="carregando" class="text-center text-muted py-4">
      Carregando...
    </div>

    <div v-else class="table-responsive">
      <table class="table table-striped table-hover align-middle">

        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Estante</th>
            <th>Prateleira</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="localizacao in localizacoes"
            :key="localizacao.id"
          >
            <td>{{ localizacao.id }}</td>
            <td>{{ localizacao.estante }}</td>
            <td>{{ localizacao.prateleira }}</td>
          </tr>

          <tr v-if="localizacoes.length === 0">
            <td colspan="3" class="text-center text-muted">
              Nenhuma localização encontrada.
            </td>
          </tr>
        </tbody>

      </table>
    </div>

  </div>
</template>