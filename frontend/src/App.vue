<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import {state} from "@/scripts/state.ts"
import TitleBar from '@/components/window/TitleBar.vue'
</script>

<template>
  <main class="app">
    <TitleBar/>
    <div class="wrapper">
      <nav class="navbar">
        <RouterLink @click="state.activePage='home'"
                    class="link"
                    :class="state.activePage === 'home' ? 'activeLink' : '' "
                    to="/">Home</RouterLink>
        <RouterLink @click="state.activePage='auth'"
                    class="link" v-if="!state.user.isLogged"
                    :class="state.activePage === 'auth' ? 'activeLink' : '' "
                    to="/registration">Register
        </RouterLink>
        <RouterLink @click="state.activePage='user'"
                    class="link" v-if="state.user.isLogged"
                    :class="state.activePage === 'user' ? 'activeLink' : '' "
                    to="/user/userid">User</RouterLink>
      </nav>
      <Suspense>
        <template #default>
          <RouterView :key="$route.fullPath" />
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
  .navbar
    display: flex
    gap: .1rem
    justify-content: center
    .link:nth-child(odd)
      padding-right: .3rem
      border-right: 1px solid rgba($lite, 0.1)
  .link
    margin-right: .3rem
    opacity: .5
    transition: all .1s ease-in-out
  .activeLink
    opacity: 1
</style>
