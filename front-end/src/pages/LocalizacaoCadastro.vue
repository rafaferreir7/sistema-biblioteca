<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { api } from '../api/api.js'

const router = useRouter()

const localizacao = ref({
  estante: '',
  prateleira: ''
})

const mensagemErro = ref('')

const salvarLocalizacao = async () => {
  mensagemErro.value = ''

  try {
    await api.post('/localizacoes', {
      estante: localizacao.value.estante,
      prateleira: localizacao.value.prateleira
    })

    alert('Localização cadastrada com sucesso!')
    router.push('/localizacoes')
  } catch (erro) {
    mensagemErro.value =
      erro.response?.data?.message ||
      'Erro ao cadastrar localização.'

    console.error('Erro na API:', erro)
  }
}
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">

        <h2 class="card-title mb-3">
          Cadastrar Localização
        </h2>

        <div
          v-if="mensagemErro"
          class="alert alert-danger"
        >
          {{ mensagemErro }}
        </div>

        <form @submit.prevent="salvarLocalizacao">

          <div class="mb-3">
            <label class="form-label">
              Estante
            </label>

            <input
              type="text"
              v-model="localizacao.estante"
              required
              class="form-control"
            />
          </div>

          <div class="mb-3">
            <label class="form-label">
              Prateleira
            </label>

            <input
              type="text"
              v-model="localizacao.prateleira"
              required
              class="form-control"
            />
          </div>

          <button
            type="submit"
            class="btn btn-success w-100"
          >
            Salvar
          </button>

        </form>
      </div>
    </div>
  </div>
</template>