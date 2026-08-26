import { createRouter, createWebHistory } from 'vue-router'

import Home from './pages/Home.vue'
import ReservaLista from './pages/ReservaLista.vue'
import ReservaCadastro from './pages/ReservaCadastro.vue'
import LeitorLista from './pages/LeitorLista.vue'
import LeitorCadastro from './pages/LeitorCadastro.vue'
import MultaLista from './pages/MultaLista.vue'
import MultaCadastro from './pages/MultaCadastro.vue'

import LivroLista from './pages/LivroLista.vue'
import LivroCadastro from './pages/LivroCadastro.vue'
import AutorLista from './pages/AutorLista.vue'
import AutorCadastro from './pages/AutorCadastro.vue'
import CategoriaLista from './pages/CategoriaLista.vue'
import CategoriaCadastro from './pages/CategoriaCadastro.vue'

const routes = [
  { path: '/', component: Home },

  { path: '/reservas', component: ReservaLista },
  { path: '/reservas/novo', component: ReservaCadastro },

  { path: '/leitores', component: LeitorLista },
  { path: '/leitores/novo', component: LeitorCadastro },

  { path: '/multas', component: MultaLista },
  { path: '/multas/novo', component: MultaCadastro },

  { path: '/livros', component: LivroLista },
  { path: '/livros/novo', component: LivroCadastro },
  { path: '/autores', component: AutorLista },
  { path: '/autores/novo', component: AutorCadastro },
  { path: '/categorias', component: CategoriaLista },
  { path: '/categorias/novo', component: CategoriaCadastro },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router