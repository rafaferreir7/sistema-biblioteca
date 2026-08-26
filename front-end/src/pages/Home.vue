<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()

const modulos = [
  { chave: 'leitores', rota: '/leitores', icone: 'bi-people-fill', cor: 'primary' },
  { chave: 'livros', rota: '/livros', icone: 'bi-book-fill', cor: 'success' },
  { chave: 'autores', rota: '/autores', icone: 'bi-pen-fill', cor: 'info' },
  { chave: 'categorias', rota: '/categorias', icone: 'bi-tags-fill', cor: 'secondary' },
  { chave: 'multas', rota: '/multas', icone: 'bi-cash-coin', cor: 'warning' },
  { chave: 'reservas', rota: '/reservas', icone: 'bi-bookmark-check-fill', cor: 'danger' }
]
</script>

<template>
  <div class="container my-5">
    <div class="text-center mb-5">
      <i class="bi bi-book display-3 text-primary"></i>
      <h1 class="mt-3">{{ t('home.titulo') }}</h1>
      <p class="text-muted">{{ t('home.subtitulo') }}</p>
    </div>

    <div class="row g-4">
      <div v-for="m in modulos" :key="m.chave" class="col-12 col-sm-6 col-lg-4">
        <router-link :to="m.rota" class="text-decoration-none">
          <div class="card h-100 shadow-sm text-center py-4 hover-card">
            <i :class="['bi', m.icone, 'display-5', `text-${m.cor}`]"></i>
            <h5 class="mt-3">{{ t(`menu.${m.chave}`) }}</h5>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.hover-card {
  transition: transform 0.15s ease, box-shadow 0.15s ease;
}
.hover-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.15) !important;
}
</style>