<script setup>
import { ref } from 'vue'
import { api } from '../api/api'

const biblioteca = ref({
  nome: '',
  email: '',
  telefone: ''
})

const salvar = async () => {
  try {
    await api.post('/bibliotecas', biblioteca.value)

    alert('Biblioteca cadastrada com sucesso!')

    biblioteca.value = {
      nome: '',
      email: '',
      telefone: ''
    }

  } catch (erro) {
    console.error(erro)
    alert('Erro ao cadastrar biblioteca.')
  }
}
</script>

<template>
  <div style="padding:20px; max-width:500px">

    <h2>Cadastro de Biblioteca</h2>

    <form @submit.prevent="salvar">

      <p>Nome</p>

      <input
        v-model="biblioteca.nome"
        type="text"
        required
      >

      <p>Email</p>

      <input
        v-model="biblioteca.email"
        type="email"
        required
      >

      <p>Telefone</p>

      <input
        v-model="biblioteca.telefone"
        type="text"
        required
      >

      <br><br>

      <button type="submit">
        Cadastrar
      </button>

    </form>

  </div>
</template>