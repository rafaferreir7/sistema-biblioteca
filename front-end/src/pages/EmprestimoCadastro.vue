<script setup>
import { ref } from 'vue'
import { api } from '../api/api'

const emprestimo = ref({
  leitorId: '',
  livroId: '',
  dataDevolucaoPrevista: ''
})

const salvar = async () => {
  try {
    await api.post('/api/emprestimos', emprestimo.value)

    alert('Empréstimo cadastrado com sucesso!')

    emprestimo.value = {
      leitorId: '',
      livroId: '',
      dataDevolucaoPrevista: ''
    }
  } catch (erro) {
    console.error('Erro ao cadastrar empréstimo:', erro)

    if (erro.response?.data) {
      console.error('Resposta do servidor:', erro.response.data)
    }

    alert('Erro ao cadastrar empréstimo.')
  }
}
</script>

<template>
  <div style="padding: 20px; max-width: 500px;">

    <h2>Cadastro de Empréstimo</h2>

    <form @submit.prevent="salvar">

      <p>ID do Leitor</p>
      <input
        v-model="emprestimo.leitorId"
        type="number"
        min="1"
        required
      >

      <p>ID do Livro</p>
      <input
        v-model="emprestimo.livroId"
        type="number"
        min="1"
        required
      >

      <p>Data de devolução prevista</p>
      <input
        v-model="emprestimo.dataDevolucaoPrevista"
        type="date"
        required
      >

      <br><br>

      <button type="submit">
        Cadastrar Empréstimo
      </button>

    </form>

  </div>
</template>