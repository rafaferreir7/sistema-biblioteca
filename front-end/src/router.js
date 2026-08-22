import { createRouter, createWebHistory } from 'vue-router';
import ReservaLista from './pages/ReservaLista.vue';
import ReservaCadastro from './pages/ReservaCadastro.vue';

const routes = [
  { path: '/reservas', component: ReservaLista },
  { path: '/reservas/novo', component: ReservaCadastro }
];

export const router = createRouter({
  history: createWebHistory(),
  routes
});