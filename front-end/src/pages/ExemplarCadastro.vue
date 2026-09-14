<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { api } from '../api/api.js'

const router = useRouter()

const exemplar = ref({
  codigoPatrimonio: '',
  disponivel: true,
  livroId: '',
  localizacaoId: ''
})

const livros = ref([])
const localizacoes = ref([])
const carregandoOpcoes = ref(true)
const mensagemErro = ref('')

const carregarOpcoes = async () => {
  carregandoOpcoes.value = true

  try {
    const [respLivros, respLocalizacoes] = await Promise.all([
      api.get('/api/livros'),
      api.get('/localizacoes')
    ])

    livros.value = respLivros.data
    localizacoes.value = respLocalizacoes.data
  } catch (erro) {
    console.error('Erro ao carregar opções:', erro)
    mensagemErro.value = 'Não foi possível carregar livros e localizações.'
  } finally {
    carregandoOpcoes.value = false
  }
}

const salvarExemplar = async () => {
  mensagemErro.value = ''

  try {
    const livroSelecionado = livros.value.find(
      livro => livro.id === Number(exemplar.value.livroId)
    )

    const localizacaoSelecionada = localizacoes.value.find(
      localizacao => localizacao.id === Number(exemplar.value.localizacaoId)
    )

    await api.post('/exemplares', {
      codigoPatrimonio: exemplar.value.codigoPatrimonio,
      disponivel: exemplar.value.disponivel,
      livro: livroSelecionado,
      localizacao: localizacaoSelecionada
    })

    alert('Exemplar cadastrado com sucesso!')
    router.push('/exemplares')
  } catch (erro) {
    mensagemErro.value =
      erro.response?.data?.message ||
      'Erro ao cadastrar exemplar.'

    console.error('Erro na API:', erro)
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

        <h2 class="card-title mb-3">
          Cadastrar Exemplar
        </h2>

        <div
          v-if="mensagemErro"
          class="alert alert-danger"
        >
          {{ mensagemErro }}
        </div>

        <form @submit.prevent="salvarExemplar">

          <div class="mb-3">
            <label class="form-label">
              Código de patrimônio
            </label>

            <input
              type="text"
              v-model="exemplar.codigoPatrimonio"
              required
              class="form-control"
            />
          </div>

          <div class="mb-3">
            <label class="form-label">
              Livro
            </label>

            <select
              v-model="exemplar.livroId"
              required
              class="form-select"
              :disabled="carregandoOpcoes"
            >
              <option value="">
                Selecione um livro
              </option>

              <option
                v-for="livro in livros"
                :key="livro.id"
                :value="livro.id"
              >
                {{ livro.titulo }}
              </option>
            </select>
          </div>

          <div class="mb-3">
            <label class="form-label">
              Localização
            </label>

            <select
              v-model="exemplar.localizacaoId"
              required
              class="form-select"
              :disabled="carregandoOpcoes"
            >
              <option value="">
                Selecione uma localização
              </option>

              <option
                v-for="localizacao in localizacoes"
                :key="localizacao.id"
                :value="localizacao.id"
              >
                Estante {{ localizacao.estante }}
                - Prateleira {{ localizacao.prateleira }}
              </option>
            </select>
          </div>

          <div class="form-check mb-3">
            <input
              id="disponivel"
              type="checkbox"
              v-model="exemplar.disponivel"
              class="form-check-input"
            />

            <label
              for="disponivel"
              class="form-check-label"
            >
              Disponível
            </label>
          </div>

          <button
            type="submit"
            class="btn btn-success w-100"
            :disabled="carregandoOpcoes"
          >
            Salvar
          </button>

        </form>
      </div>
    </div>
  </div>
</template>