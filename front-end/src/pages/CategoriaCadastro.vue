<script setup>
import { ref } from 'vue'
import api from '../api/api.js'

const nome = ref('')
const descricao = ref('')
const mensagem = ref('')
const erro = ref('')

async function salvar() {
  mensagem.value = ''
  erro.value = ''
  try {
    await api.post('/categorias', {
      nome: nome.value,
      descricao: descricao.value
    })
    mensagem.value = 'Categoria cadastrada com sucesso!'
    nome.value = ''
    descricao.value = ''
  } catch (e) {
    erro.value = e.response?.data?.message || 'Erro ao cadastrar categoria.'
  }
}
</script>

<template>
  <div>
    <h2>Cadastrar Categoria</h2>
    <form @submit.prevent="salvar">
      <div>
        <label>Nome:</label>
        <input v-model="nome" type="text" />
      </div>
      <div>
        <label>Descrição:</label>
        <input v-model="descricao" type="text" />
      </div>
      <button type="submit">Salvar</button>
    </form>
    <p v-if="mensagem" style="color: green">{{ mensagem }}</p>
    <p v-if="erro" style="color: red">{{ erro }}</p>
  </div>
</template>