<script setup lang="ts">
/**
 * Questa View si occupa di gestire la registrazione dell'utente
 * @author Leonardo Basso
 */
import { RouterLink } from 'vue-router'
import {state} from "@/scripts/state.ts";
</script>

<template>
  <section class="register">
    <p v-if="state.user.isLogged"> Sei già loggato come <u>{{ state.user.userId }}</u></p>
    <div v-if="!state.user.isLogged" class="register__content">
      <div class="register__double_input">
        <input type="text" placeholder="Nome" />
        <input type="text" placeholder="Cognome" />
      </div>
      <div class="register__double_input">
        <input type="text" placeholder="UserID" />
        <input type="text" placeholder="Codice Fiscale" />
      </div>
      <input class="register__single_input" type="email" placeholder="Email" />
      <input class="register__single_input" type="password" placeholder="Password" />
      <input class="register__single_input" type="password" placeholder="Conferma Password" />
      <button>Register</button>
      <p>
        Sei registrato? allora
        <RouterLink to="/login">fai il Log In</RouterLink>
      </p>
    </div>
  </section>
</template>

<style scoped lang="sass">
.register
  display: grid
  justify-content: center
  align-items: center
  height: calc(100vh - 5rem)

  .register__content
    width: 400px
    max-width: 80vw
    text-align: center
    >*
      margin-bottom: .6rem
    input
      width: 100%
    .register__double_input
      display: flex
      gap: .6rem
</style>
