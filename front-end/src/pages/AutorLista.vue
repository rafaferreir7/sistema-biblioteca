<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api.js'

const autores = ref([])

const buscarAutores = async () => {
  try {
    const resposta = await api.get('/api/autores')
    autores.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar autores:", erro)
  }
}

onMounted(() => {
  buscarAutores()
})
</script>

<template>
  <div style="padding: 20px;">
    <h2>Lista de Autores</h2>
    <button @click="buscarAutores" style="margin-bottom: 15px; padding: 5px 10px;">Atualizar Lista</button>

    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
      <thead style="background-color: #f2f2f2;">
        <tr>
          <th style="padding: 8px;">ID</th>
          <th style="padding: 8px;">Nome</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="autor in autores" :key="autor.id">
          <td style="padding: 8px;">{{ autor.id }}</td>
          <td style="padding: 8px;">{{ autor.nome }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="autores.length === 0" style="color: gray;">Nenhum autor encontrado no banco de dados.</p>
  </div>
</template>