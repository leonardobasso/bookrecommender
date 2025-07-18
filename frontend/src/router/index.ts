import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LogInView from '@/views/auth/LogInView.vue'
import RegistrationView from '@/views/auth/RegistrationView.vue'
import BookView from '@/views/books/BookView.vue'
import UserView from '@/views/UserView.vue'
import LibraryBookView from "@/views/books/LibraryBookView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: 'login',
      component: LogInView,
    },
    {
      path: '/registration',
      name: 'register',
      component: RegistrationView,
    },
    {
      path: '/books/book/:id',
      name: 'book',
      component: BookView,
      props: true,
    },
    {
      path: '/library/books/:id',
      name: 'libBooks',
      component: LibraryBookView,
      props: true,
    },
    {
      path: '/user/userid',
      name: 'user',
      component: UserView,
    },
  ],
})
/**
 * Il router collega un URL a una View, nell'ambito della App, il router viene usato per mostrare le
 * diverse pagine del software
 * @author Leonardo Basso
 */
export default router
