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

export const router = createRouter({
  history: createWebHistory(),
  routes
});