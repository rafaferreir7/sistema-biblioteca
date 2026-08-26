<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api'

const emprestimos = ref([])

const buscarEmprestimos = async () => {
  try {
    const resposta = await api.get('/api/emprestimos')
    emprestimos.value = resposta.data
  } catch (erro) {
    console.error('Erro ao buscar empréstimos:', erro)
  }
}

onMounted(() => {
  buscarEmprestimos()
})
</script>

<template>
  <div style="padding: 20px;">

    <h2>Lista de Empréstimos</h2>

    <button @click="buscarEmprestimos">
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
          <th>ID do Leitor</th>
          <th>ID do Livro</th>
          <th>Data do Empréstimo</th>
          <th>Devolução Prevista</th>
          <th>Status</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="emprestimo in emprestimos"
          :key="emprestimo.id"
        >
          <td>{{ emprestimo.id }}</td>
          <td>{{ emprestimo.leitorId }}</td>
          <td>{{ emprestimo.livroId }}</td>
          <td>{{ emprestimo.dataEmprestimo }}</td>
          <td>{{ emprestimo.dataDevolucaoPrevista }}</td>
          <td>{{ emprestimo.status }}</td>
        </tr>
      </tbody>
    </table>

    <p v-if="emprestimos.length === 0">
      Nenhum empréstimo cadastrado.
    </p>

  </div>
</template>