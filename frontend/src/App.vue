<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import {state} from "@/scripts/state.ts"
import TitleBar from '@/components/window/TitleBar.vue'
</script>

<template>
  <main class="app">
      <TitleBar />
      <div class="wrapper">
        <nav>
          <RouterLink class="link" to="/">Home</RouterLink>
          <RouterLink class="link" v-if="!state.user.isLogged" to="/registration" >Register</RouterLink>
          <RouterLink class="link" v-if="state.user.isLogged" to="/user/userid">User</RouterLink>
        </nav>
        <Suspense>
          <template #default>
            <RouterView />
          </template>
          <template #fallback>
            <div style="color: red">Caricamento in corso...</div>
          </template>
        </Suspense>
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
  .link
    margin-right: .3rem
</style>
