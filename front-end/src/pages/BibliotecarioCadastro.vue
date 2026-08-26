<script setup>
import { ref } from 'vue'
import { api } from '../api/api'

const bibliotecario = ref({
  nome: '',
  cpf: '',
  email: '',
  telefone: '',
  cargo: '',
  dataAdmissao: ''
})

const salvar = async () => {
  try {
    await api.post('/bibliotecarios', bibliotecario.value)

    alert('Bibliotecário cadastrado com sucesso!')

    bibliotecario.value = {
      nome: '',
      cpf: '',
      email: '',
      telefone: '',
      cargo: '',
      dataAdmissao: ''
    }

  } catch (erro) {
    console.error('Erro ao cadastrar bibliotecário:', erro)

    if (erro.response?.data) {
      console.error('Resposta do servidor:', erro.response.data)
    }

    alert('Erro ao cadastrar bibliotecário.')
  }
}
</script>

<template>
  <div style="padding: 20px; max-width: 500px;">

    <h2>Cadastro de Bibliotecário</h2>

    <form @submit.prevent="salvar">

      <p>Nome</p>
      <input
        v-model="bibliotecario.nome"
        type="text"
        required
      >

      <p>CPF</p>
      <input
        v-model="bibliotecario.cpf"
        type="text"
        required
      >

      <p>Email</p>
      <input
        v-model="bibliotecario.email"
        type="email"
        required
      >

      <p>Telefone</p>
      <input
        v-model="bibliotecario.telefone"
        type="text"
        required
      >

      <p>Cargo</p>
      <input
        v-model="bibliotecario.cargo"
        type="text"
        required
      >

      <p>Data de admissão</p>
      <input
        v-model="bibliotecario.dataAdmissao"
        type="date"
        required
      >

      <br><br>

      <button type="submit">
        Cadastrar
      </button>

    </form>

  </div>
</template>