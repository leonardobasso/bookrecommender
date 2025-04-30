<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import TitleBar from '@/components/window/TitleBar.vue'
</script>

<template>
  <main class="app">
      <TitleBar />
      <div class="wrapper">
        <nav>
          <RouterLink to="/">Home</RouterLink>
          <RouterLink style="margin: 0 .3rem" to="/login">Login</RouterLink>
          <RouterLink to="/registration">Register</RouterLink>
          <RouterLink to="/books/book">Libro</RouterLink>
        </nav>
        <RouterView />
      </div>
  </main>
</template>

<style scoped lang="sass">
.app
  display: grid
  .wrapper
    flex: 1
    margin: 2.5rem 1rem
    overflow-y: scroll
    height: calc(100vh - 2.5rem)
    width: calc(100vw - 2rem)
</style>
