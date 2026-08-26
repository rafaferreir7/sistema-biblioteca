<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { api } from '../api/api.js'

const router = useRouter()
const { t } = useI18n()
const nomes = ref('')
const salvando = ref(false)
const erro = ref('')

async function salvarCategorias() {
  erro.value = ''
  const lista = nomes.value
    .split('\n')
    .map(l => l.trim())
    .filter(l => l.length > 0)

  if (lista.length === 0) {
    erro.value = t('categoria.erroVazio')
    return
  }

  salvando.value = true
  try {
    for (const nome of lista) {
      await api.post('/api/categorias', { nome, descricao: nome })
    }
    alert(t('categoria.sucessoLote', { n: lista.length }))
    router.push('/categorias')
  } catch (e) {
    erro.value = t('categoria.erroSalvar')
    console.error(e)
  } finally {
    salvando.value = false
  }
}
</script>

<template>
  <div class="container my-4" style="max-width: 500px;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-3">{{ t('categoria.cadastrarTitulo') }}</h2>
        <div v-if="erro" class="alert alert-danger">{{ erro }}</div>

        <form @submit.prevent="salvarCategorias">
          <div class="mb-2">
            <label class="form-label">{{ t('categoria.nomesLabel') }}</label>
            <textarea v-model="nomes" required class="form-control" rows="8" :placeholder="t('categoria.nomesPlaceholder')"></textarea>
            <div class="form-text">{{ t('categoria.nomesDica') }}</div>
          </div>

          <button type="submit" class="btn btn-success w-100" :disabled="salvando">
            {{ salvando ? t('geral.salvando') : t('categoria.salvar') }}
          </button>
        </form>
      </div>
    </div>
  </div>
</template>