import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LogInView from '@/views/auth/LogInView.vue'
import RegistrationView from '@/views/auth/RegistrationView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: LogInView
    },
    {
      path: '/registration',
      name: 'register',
      component: RegistrationView
    }
  ],
})
/**
 * Il router collega un URL a una View, nell'ambito della App, il router viene usato per mostrare le
 * diverse pagine del software
 * @author Leonardo Basso
 */
export default router
