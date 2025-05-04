<script setup lang="ts">
/**
 * Questa View si occupa di gestire il Log-In dell'utente
 * @author Leonardo Basso
 */
import { RouterLink } from 'vue-router'
import { state } from '@/scripts/state.ts'
</script>

<template>
  <p v-if="state.user.isLogged"> Sei già loggato come <u>{{state.user.userId}}</u></p>
  <section v-if="!state.user.isLogged" class="register">
    <input type="text" placeholder="UserID" />
    <input type="password" placeholder="Password..." />
    <button>Log In</button>
    <p>Non sei ancora registrato? allora
      <RouterLink to="/registration">registrati!</RouterLink>
    </p>
  </section>
</template>

<style scoped lang="sass">
.register
  height: calc(100vh - 5rem)
  display: flex
  flex-direction: column
  justify-content: center
  align-items: center
  gap: .6rem
  input
    width: 400px
    max-width: 80vw
</style>
