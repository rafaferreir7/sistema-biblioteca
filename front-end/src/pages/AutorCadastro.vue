<script setup>
import { ref } from 'vue'
import api from '../api/api.js'

const nome = ref('')
const mensagem = ref('')
const erro = ref('')

async function salvar() {
  mensagem.value = ''
  erro.value = ''
  try {
    await api.post('/autores', {
      nome: nome.value
    })
    mensagem.value = 'Autor cadastrado com sucesso!'
    nome.value = ''
  } catch (e) {
    erro.value = e.response?.data?.message || 'Erro ao cadastrar autor.'
  }
}
</script>

<template>
  <div>
    <h2>Cadastrar Autor</h2>
    <form @submit.prevent="salvar">
      <div>
        <label>Nome:</label>
        <input v-model="nome" type="text" />
      </div>
      <button type="submit">Salvar</button>
    </form>
    <p v-if="mensagem" style="color: green">{{ mensagem }}</p>
    <p v-if="erro" style="color: red">{{ erro }}</p>
  </div>
</template>