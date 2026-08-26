<<<<<<< HEAD
import { createRouter, createWebHistory } from 'vue-router';

import EmprestimoLista from './pages/EmprestimoLista.vue'
import EmprestimoCadastro from './pages/EmprestimoCadastro.vue'
import BibliotecarioLista from './pages/BibliotecarioLista.vue'
import BibliotecarioCadastro from './pages/BibliotecarioCadastro.vue'
import ReservaLista from './pages/ReservaLista.vue';
import ReservaCadastro from './pages/ReservaCadastro.vue';
import BibliotecaLista from './pages/BibliotecaLista.vue'
import BibliotecaCadastro from './pages/BibliotecaCadastro.vue'

const routes = [
  
  {
  path: '/emprestimos',
  component: EmprestimoLista
},
{
  path: '/emprestimos/novo',
  component: EmprestimoCadastro
},
  {
  path: '/bibliotecarios',
  component: BibliotecarioLista
},
{
  path: '/bibliotecarios/novo',
  component: BibliotecarioCadastro
},
  {
  path: '/bibliotecas',
  component: BibliotecaLista
},
{
  path: '/bibliotecas/novo',
  component: BibliotecaCadastro
},
  {
    path: '/',
    redirect: '/reservas'
  },
  {
    path: '/reservas',
    component: ReservaLista
  },
  {
    path: '/reservas/novo',
    component: ReservaCadastro
  }
];
=======
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
>>>>>>> fb71eedcde9b06bee2ae21606b4b2c144ccf7b36

export const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router