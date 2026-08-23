<script setup>
import { ref, onMounted } from 'vue'
import { api } from '../api/api.js'

const livro = ref({
  titulo: '',
  isbn: '',
  ano: '',
  quantidade: '',
  categoriaId: '',
  autorIds: []
})

const categorias = ref([])
const autores = ref([])

const carregarOpcoes = async () => {
  try {
    const respCategorias = await api.get('/categorias')
    categorias.value = respCategorias.data

    const respAutores = await api.get('/autores')
    autores.value = respAutores.data
  } catch (erro) {
    console.error("Erro ao carregar categorias/autores:", erro)
  }
}

const salvarLivro = async () => {
  try {
    await api.post('/livros', {
      titulo: livro.value.titulo,
      isbn: livro.value.isbn,
      ano: Number(livro.value.ano),
      quantidade: Number(livro.value.quantidade),
      categoriaId: Number(livro.value.categoriaId),
      autorIds: livro.value.autorIds.map(id => Number(id))
    })
    alert("Livro cadastrado com sucesso!")
    livro.value = { titulo: '', isbn: '', ano: '', quantidade: '', categoriaId: '', autorIds: [] }
  } catch (erro) {
    alert("Erro ao salvar. Verifique se o back-end Java está rodando.")
    console.error("Erro na API:", erro)
  }
}

onMounted(() => {
  carregarOpcoes()
})
</script>

<template>
  <div style="padding: 20px; max-width: 400px;">
    <h2>Cadastrar Novo Livro</h2>

    <form @submit.prevent="salvarLivro" style="display: flex; flex-direction: column; gap: 15px;">
      <div>
        <label>Título:</label>
        <input type="text" v-model="livro.titulo" required style="width: 100%; padding: 8px;" />
      </div>

      <div>
        <label>ISBN:</label>
        <input type="text" v-model="livro.isbn" required style="width: 100%; padding: 8px;" />
      </div>

      <div>
        <label>Ano:</label>
        <input type="number" v-model="livro.ano" required style="width: 100%; padding: 8px;" />
      </div>

      <div>
        <label>Quantidade:</label>
        <input type="number" v-model="livro.quantidade" required style="width: 100%; padding: 8px;" />
      </div>

      <div>
        <label>Categoria:</label>
        <select v-model="livro.categoriaId" required style="width: 100%; padding: 8px;">
          <option value="">Selecione</option>
          <option v-for="c in categorias" :key="c.id" :value="c.id">{{ c.nome }}</option>
        </select>
      </div>

      <div>
        <label>Autor(es) (segure Ctrl para selecionar mais de um):</label>
        <select v-model="livro.autorIds" multiple style="width: 100%; padding: 8px;">
          <option v-for="a in autores" :key="a.id" :value="a.id">{{ a.nome }}</option>
        </select>
      </div>

      <button type="submit" style="padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
        Salvar Livro
      </button>
    </form>
  </div>
</template>e>