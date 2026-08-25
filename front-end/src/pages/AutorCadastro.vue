<script setup>
import { ref } from 'vue'
import { api } from '../api/api.js'

const autor = ref({ nome: '' })

const salvarAutor = async () => {
  try {
    await api.post('/api/autores', autor.value)
    alert("Autor cadastrado com sucesso!")
    autor.value = { nome: '' }
  } catch (erro) {
    alert("Erro ao salvar. Verifique se o back-end Java está rodando.")
    console.error("Erro na API:", erro)
  }
}
</script>

<template>
  <div style="padding: 20px; max-width: 400px;">
    <h2>Cadastrar Novo Autor</h2>

    <form @submit.prevent="salvarAutor" style="display: flex; flex-direction: column; gap: 15px;">
      <div>
        <label>Nome:</label>
        <input type="text" v-model="autor.nome" required style="width: 100%; padding: 8px;" />
      </div>

      <button type="submit" style="padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
        Salvar Autor
      </button>
    </form>
  </div>
</template>