<script setup>
import { ref, onMounted } from 'vue'
import api from '../api/api.js'

const categorias = ref([])
const erro = ref('')

async function carregar() {
  try {
    const resposta = await api.get('/categorias')
    categorias.value = resposta.data
  } catch (e) {
    erro.value = 'Erro ao carregar categorias.'
  }
}

onMounted(carregar)
</script>

<template>
  <div>
    <h2>Categorias</h2>
    <p v-if="erro" style="color: red">{{ erro }}</p>
    <ul>
      <li v-for="categoria in categorias" :key="categoria.id">
        <strong>{{ categoria.nome }}</strong> — {{ categoria.descricao }}
      </li>
    </ul>
  </div>
</template>