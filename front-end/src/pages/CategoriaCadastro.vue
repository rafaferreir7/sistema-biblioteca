<script setup>
import { ref } from 'vue'
import { api } from '../api/api.js'

const categoria = ref({ nome: '', descricao: '' })

const salvarCategoria = async () => {
  try {
    await api.post('/api/categorias', categoria.value)
    alert("Categoria cadastrada com sucesso!")
    categoria.value = { nome: '', descricao: '' }
  } catch (erro) {
    alert("Erro ao salvar. Verifique se o back-end Java está rodando.")
    console.error("Erro na API:", erro)
  }
}
</script>

<template>
  <div style="padding: 20px; max-width: 400px;">
    <h2>Cadastrar Nova Categoria</h2>

    <form @submit.prevent="salvarCategoria" style="display: flex; flex-direction: column; gap: 15px;">
      <div>
        <label>Nome:</label>
        <input type="text" v-model="categoria.nome" required style="width: 100%; padding: 8px;" />
      </div>

      <div>
        <label>Descrição:</label>
        <input type="text" v-model="categoria.descricao" required style="width: 100%; padding: 8px;" />
      </div>

      <button type="submit" style="padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
        Salvar Categoria
      </button>
    </form>
  </div>
</template>