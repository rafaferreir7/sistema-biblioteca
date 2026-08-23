<script setup>
import { ref, onMounted } from 'vue'
import api from '../api/api.js'

const livros = ref([])
const erro = ref('')

async function carregar() {
  try {
    const resposta = await api.get('/livros')
    livros.value = resposta.data
  } catch (e) {
    erro.value = 'Erro ao carregar livros.'
  }
}

onMounted(carregar)
</script>

<template>
  <div>
    <h2>Livros</h2>
    <p v-if="erro" style="color: red">{{ erro }}</p>
    <ul>
      <li v-for="livro in livros" :key="livro.id">
        <strong>{{ livro.titulo }}</strong> — ISBN: {{ livro.isbn }} —
        Categoria: {{ livro.categoria?.nome }} —
        Autor(es): {{ livro.autores?.map(a => a.nome).join(', ') }}
      </li>
    </ul>
  </div>
</template>