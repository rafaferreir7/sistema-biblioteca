<script setup>
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
const { t, locale } = useI18n()

function trocarIdioma(novo) {
  locale.value = novo
  localStorage.setItem('idioma', novo)
}

const temaEscuro = ref(false)

function trocarTema() {
  temaEscuro.value = !temaEscuro.value
  const tema = temaEscuro.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-bs-theme', tema)
  localStorage.setItem('tema', tema)
}

onMounted(() => {
  const temaSalvo = localStorage.getItem('tema') || 'light'
  temaEscuro.value = temaSalvo === 'dark'
  document.documentElement.setAttribute('data-bs-theme', temaSalvo)
})
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-4">
    <router-link to="/" class="navbar-brand text-decoration-none">
      <i class="bi bi-book"></i> {{ t('menu.titulo') }}
    </router-link>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="menuNav">
      <ul class="navbar-nav me-auto">

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.reservas') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/reservas">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/reservas/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.leitores') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/leitores">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/leitores/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.multas') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/multas">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/multas/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.livros') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/livros">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/livros/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.autores') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/autores">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/autores/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">{{ t('menu.categorias') }}</a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" to="/categorias">{{ t('menu.listar') }}</router-link></li>
            <li><router-link class="dropdown-item" to="/categorias/novo">{{ t('menu.novo') }}</router-link></li>
          </ul>
        </li>

      </ul>

      <button class="btn btn-outline-light btn-sm me-2" @click="trocarTema" type="button">
        <i :class="temaEscuro ? 'bi bi-sun' : 'bi bi-moon-stars'"></i>
      </button>

      <select class="form-select form-select-sm w-auto" :value="locale" @change="trocarIdioma($event.target.value)">
        <option value="pt">🇧🇷 PT</option>
        <option value="en">🇺🇸 EN</option>
      </select>
    </div>
  </nav>
</template>