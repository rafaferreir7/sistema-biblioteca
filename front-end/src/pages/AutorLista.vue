<script setup>
import { ref, onMounted } from 'vue'
import api from '../api/api.js'

const autores = ref([])
const erro = ref('')

async function carregar() {
  try {
    const resposta = await api.get('/autores')
    autores.value = resposta.data
  } catch (e) {
    erro.value = 'Erro ao carregar autores.'
  }
}

onMounted(carregar)
</script>

<template>
  <div>
    <h2>Autores</h2>
    <p v-if="erro" style="color: red">{{ erro }}</p>
    <ul>
      <li v-for="autor in autores" :key="autor.id">
        {{ autor.nome }}
      </li>
    </ul>
  </div>
</template>