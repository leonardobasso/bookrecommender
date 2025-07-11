<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import TitleBar from '@/components/window/TitleBar.vue'
</script>

<template>
  <Suspense>

  <main class="app">
      <TitleBar />
      <div class="wrapper">
        <nav>
          <RouterLink to="/">Home</RouterLink>
          <RouterLink to="/registration" style="margin: 0 .3rem" >Register</RouterLink>
          <RouterLink to="/user/userid">User</RouterLink>
        </nav>
        <RouterView />
      </div>
  </main>
  </Suspense>
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
