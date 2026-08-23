<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api.js'

const livros = ref([])

const buscarLivros = async () => {
  try {
    const resposta = await api.get('/livros')
    livros.value = resposta.data
  } catch (erro) {
    console.error("Erro ao buscar livros:", erro)
  }
}

onMounted(() => {
  buscarLivros()
})
</script>

<template>
  <div style="padding: 20px;">
    <h2>Lista de Livros</h2>
    <button @click="buscarLivros" style="margin-bottom: 15px; padding: 5px 10px;">Atualizar Lista</button>

    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
      <thead style="background-color: #f2f2f2;">
        <tr>
          <th style="padding: 8px;">ID</th>
          <th style="padding: 8px;">Título</th>
          <th style="padding: 8px;">ISBN</th>
          <th style="padding: 8px;">Categoria</th>
          <th style="padding: 8px;">Autor(es)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="livro in livros" :key="livro.id">
          <td style="padding: 8px;">{{ livro.id }}</td>
          <td style="padding: 8px;">{{ livro.titulo }}</td>
          <td style="padding: 8px;">{{ livro.isbn }}</td>
          <td style="padding: 8px;">{{ livro.categoria?.nome }}</td>
          <td style="padding: 8px;">{{ livro.autores?.map(a => a.nome).join(', ') }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="livros.length === 0" style="color: gray;">Nenhum livro encontrado no banco de dados.</p>
  </div>
</template>