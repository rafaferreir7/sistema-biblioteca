<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api'

const bibliotecas = ref([])

const buscarBibliotecas = async () => {
  try {
    const resposta = await api.get('/bibliotecas')
    bibliotecas.value = resposta.data
  } catch (erro) {
    console.error('Erro ao buscar bibliotecas:', erro)
  }
}

onMounted(() => {
  buscarBibliotecas()
})
</script>

<template>
  <div style="padding:20px">
    <h2>Lista de Bibliotecas</h2>

    <button @click="buscarBibliotecas">
      Atualizar Lista
    </button>

    <br><br>

    <table border="1" style="width:100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Email</th>
          <th>Telefone</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="biblioteca in bibliotecas"
          :key="biblioteca.id"
        >
          <td>{{ biblioteca.id }}</td>
          <td>{{ biblioteca.nome }}</td>
          <td>{{ biblioteca.email }}</td>
          <td>{{ biblioteca.telefone }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="bibliotecas.length === 0">
      Nenhuma biblioteca cadastrada.
    </p>
  </div>
</template>