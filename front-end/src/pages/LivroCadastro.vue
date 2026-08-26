<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()

const livro = ref({
  titulo: '',
  isbn: '',
  ano: '',
  quantidade: '',
  categoriaId: '',
  autoresTexto: ''
})

const categorias = ref([])
const carregandoOpcoes = ref(true)

const carregarOpcoes = async () => {
  carregandoOpcoes.value = true
  try {
    const respCategorias = await api.get('/api/categorias')
    categorias.value = respCategorias.data
  } catch (erro) {
    console.error("Erro ao carregar categorias:", erro)
  } finally {
    carregandoOpcoes.value = false
  }
}

async function resolverAutorIds(nomesTexto) {
  const nomes = nomesTexto
    .split(',')
    .map(n => n.trim())
    .filter(n => n.length > 0)

  const respAutores = await api.get('/api/autores')
  const autoresExistentes = respAutores.data

  const ids = []
  for (const nome of nomes) {
    const encontrado = autoresExistentes.find(
      a => a.nome.toLowerCase() === nome.toLowerCase()
    )
    if (encontrado) {
      ids.push(encontrado.id)
    } else {
      const criado = await api.post('/api/autores', { nome })
      ids.push(criado.data.id)
    }
  }
  return ids
}

const salvarLivro = async () => {
  try {
    const autorIds = await resolverAutorIds(livro.value.autoresTexto)

    await api.post('/api/livros', {
      titulo: livro.value.titulo,
      isbn: livro.value.isbn,
      ano: Number(livro.value.ano),
      quantidade: Number(livro.value.quantidade),
      categoriaId: Number(livro.value.categoriaId),
      autorIds
    })
    alert(t('livro.sucesso'))
    router.push('/livros')
  } catch (erro) {
    alert(t('livro.erroSalvar'))
    console.error("Erro na API:", erro)
  }
}

onMounted(() => {
  carregarOpcoes()
})
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('livro.cadastrarTitulo') }}</h2>

        <form @submit.prevent="salvarLivro">
          <div class="mb-3">
            <label class="form-label">{{ t('livro.titulo') }}</label>
            <input type="text" v-model="livro.titulo" required class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('livro.isbn') }}</label>
            <input type="text" v-model="livro.isbn" required class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('livro.ano') }}</label>
            <input type="number" v-model="livro.ano" required class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('livro.quantidade') }}</label>
            <input type="number" v-model="livro.quantidade" required class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('livro.categoria') }}</label>
            <select v-model="livro.categoriaId" required class="form-select" :disabled="carregandoOpcoes">
              <option value="">{{ t('livro.selecione') }}</option>
              <option v-for="c in categorias" :key="c.id" :value="c.id">{{ c.nome }}</option>
            </select>
          </div>

          <div class="mb-3">
            <label class="form-label">{{ t('livro.autoresLabel') }}</label>
            <input type="text" v-model="livro.autoresTexto" required class="form-control" placeholder="Ex: J.K. Rowling, George Orwell" />
            <div class="form-text">{{ t('livro.autoresDicaTexto') }}</div>
          </div>

          <button type="submit" class="btn btn-success w-100">{{ t('livro.salvar') }}</button>
        </form>
      </div>
    </div>
  </div>
</template>