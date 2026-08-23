import { createRouter, createWebHistory } from 'vue-router'

import LivroLista from './pages/LivroLista.vue'
import LivroCadastro from './pages/LivroCadastro.vue'
import AutorLista from './pages/AutorLista.vue'
import AutorCadastro from './pages/AutorCadastro.vue'
import CategoriaLista from './pages/CategoriaLista.vue'
import CategoriaCadastro from './pages/CategoriaCadastro.vue'

const routes = [
  { path: '/livros', component: LivroLista },
  { path: '/livros/novo', component: LivroCadastro },
  { path: '/autores', component: AutorLista },
  { path: '/autores/novo', component: AutorCadastro },
  { path: '/categorias', component: CategoriaLista },
  { path: '/categorias/novo', component: CategoriaCadastro },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router