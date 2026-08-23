import { createRouter, createWebHistory } from 'vue-router';
import ReservaLista from './pages/ReservaLista.vue';
import ReservaCadastro from './pages/ReservaCadastro.vue';

import LeitorLista from './pages/LeitorLista.vue';
import LeitorCadastro from './pages/LeitorCadastro.vue';
import MultaLista from './pages/MultaLista.vue';
import MultaCadastro from './pages/MultaCadastro.vue';

const routes = [
  { path: '/reservas', component: ReservaLista },
  { path: '/reservas/novo', component: ReservaCadastro },

  { path: '/leitores', component: LeitorLista },
  { path: '/leitores/novo', component: LeitorCadastro },

  { path: '/multas', component: MultaLista },
  { path: '/multas/novo', component: MultaCadastro }
];

export const router = createRouter({
  history: createWebHistory(),
  routes
});