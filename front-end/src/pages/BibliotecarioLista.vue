<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api'

const bibliotecarios = ref([])

const buscarBibliotecarios = async () => {
  try {
    const resposta = await api.get('/bibliotecarios')
    bibliotecarios.value = resposta.data
  } catch (erro) {
    console.error('Erro ao buscar bibliotecários:', erro)
  }
}

onMounted(() => {
  buscarBibliotecarios()
})
</script>

<template>
  <div style="padding: 20px;">
    <h2>Lista de Bibliotecários</h2>

    <button @click="buscarBibliotecarios">
      Atualizar Lista
    </button>

    <br><br>

    <table
      border="1"
      style="width: 100%; border-collapse: collapse;"
    >
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>CPF</th>
          <th>Email</th>
          <th>Telefone</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="bibliotecario in bibliotecarios"
          :key="bibliotecario.id"
        >
          <td>{{ bibliotecario.id }}</td>
          <td>{{ bibliotecario.nome }}</td>
          <td>{{ bibliotecario.cpf }}</td>
          <td>{{ bibliotecario.email }}</td>
          <td>{{ bibliotecario.telefone }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="bibliotecarios.length === 0">
      Nenhum bibliotecário cadastrado.
    </p>
  </div>
</template>