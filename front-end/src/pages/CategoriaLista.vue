<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api.js'

const categorias = ref([])

const buscarCategorias = async () => {
  try {
    const resposta = await api.get('/api/categorias')
    categorias.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar categorias:", erro)
  }
}

onMounted(() => {
  buscarCategorias()
})
</script>

<template>
  <div style="padding: 20px;">
    <h2>Lista de Categorias</h2>
    <button @click="buscarCategorias" style="margin-bottom: 15px; padding: 5px 10px;">Atualizar Lista</button>

    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
      <thead style="background-color: #f2f2f2;">
        <tr>
          <th style="padding: 8px;">ID</th>
          <th style="padding: 8px;">Nome</th>
          <th style="padding: 8px;">Descrição</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="categoria in categorias" :key="categoria.id">
          <td style="padding: 8px;">{{ categoria.id }}</td>
          <td style="padding: 8px;">{{ categoria.nome }}</td>
          <td style="padding: 8px;">{{ categoria.descricao }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="categorias.length === 0" style="color: gray;">Nenhuma categoria encontrada no banco de dados.</p>
  </div>
</template>