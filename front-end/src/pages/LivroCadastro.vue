<script setup>
import { ref, onMounted } from 'vue'
import api from '../api/api.js'

const titulo = ref('')
const isbn = ref('')
const ano = ref('')
const quantidade = ref('')
const categoriaId = ref('')
const autorIds = ref([])

const categorias = ref([])
const autores = ref([])

const mensagem = ref('')
const erro = ref('')

async function carregarOpcoes() {
  try {
    const respCategorias = await api.get('/categorias')
    categorias.value = respCategorias.data

    const respAutores = await api.get('/autores')
    autores.value = respAutores.data
  } catch (e) {
    erro.value = 'Erro ao carregar categorias/autores.'
  }
}

async function salvar() {
  mensagem.value = ''
  erro.value = ''
  try {
    await api.post('/livros', {
      titulo: titulo.value,
      isbn: isbn.value,
      ano: Number(ano.value),
      quantidade: Number(quantidade.value),
      categoriaId: Number(categoriaId.value),
      autorIds: autorIds.value.map(id => Number(id))
    })
    mensagem.value = 'Livro cadastrado com sucesso!'
    titulo.value = ''
    isbn.value = ''
    ano.value = ''
    quantidade.value = ''
    categoriaId.value = ''
    autorIds.value = []
  } catch (e) {
    erro.value = e.response?.data?.message || 'Erro ao cadastrar livro.'
  }
}

onMounted(carregarOpcoes)
</script>

<template>
  <div>
    <h2>Cadastrar Livro</h2>
    <form @submit.prevent="salvar">
      <div>
        <label>Título:</label>
        <input v-model="titulo" type="text" />
      </div>
      <div>
        <label>ISBN:</label>
        <input v-model="isbn" type="text" />
      </div>
      <div>
        <label>Ano:</label>
        <input v-model="ano" type="number" />
      </div>
      <div>
        <label>Quantidade:</label>
        <input v-model="quantidade" type="number" />
      </div>
      <div>
        <label>Categoria:</label>
        <select v-model="categoriaId">
          <option value="">Selecione</option>
          <option v-for="c in categorias" :key="c.id" :value="c.id">
            {{ c.nome }}
          </option>
        </select>
      </div>
      <div>
        <label>Autor(es) (segure Ctrl para selecionar mais de um):</label>
        <select v-model="autorIds" multiple>
          <option v-for="a in autores" :key="a.id" :value="a.id">
            {{ a.nome }}
          </option>
        </select>
      </div>
      <button type="submit">Salvar</button>
    </form>
    <p v-if="mensagem" style="color: green">{{ mensagem }}</p>
    <p v-if="erro" style="color: red">{{ erro }}</p>
  </div>
</template>