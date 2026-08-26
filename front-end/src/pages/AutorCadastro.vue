<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()
const autor = ref({ nome: '' })

const salvarAutor = async () => {
  try {
    await api.post('/api/autores', autor.value)
    alert(t('autor.sucesso'))
    router.push('/autores')
  } catch (erro) {
    alert(t('autor.erroSalvar'))
    console.error("Erro na API:", erro)
  }
}
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('autor.cadastrarTitulo') }}</h2>

        <form @submit.prevent="salvarAutor">
          <div class="mb-3">
            <label class="form-label">{{ t('autor.nome') }}</label>
            <input type="text" v-model="autor.nome" required class="form-control" />
          </div>

          <button type="submit" class="btn btn-success w-100">{{ t('autor.salvar') }}</button>
        </form>
      </div>
    </div>
  </div>
</template>