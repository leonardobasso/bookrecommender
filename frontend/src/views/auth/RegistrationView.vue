<script setup lang="ts">
/**
 * Questa View si occupa di gestire la registrazione dell'utente
 * @author Leonardo Basso
 */
import { RouterLink } from 'vue-router'
</script>

<template>
  <section class="register">
    <div class="register__double_input">
      <input type="text" placeholder="Nome" />
      <input type="text" placeholder="Cognome" />
    </div>
    <div class="register__double_input">
      <input type="text" placeholder="UserID" />
      <input type="text" placeholder="Codice Fiscale" />
    </div>
    <input type="email" placeholder="Email" />
    <input type="password" placeholder="Password" />
    <input type="password" placeholder="Conferma Password" />
    <button>Register</button>
    <p>Sei registrato? allora
      <RouterLink to="/login">fai il Log In</RouterLink>
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

  input, .register__double_input
    width: 400px
    max-width: 80vw
    display: flex

  .register__double_input
    gap: .6rem
    input
      width: calc(200px - .6rem)
      max-width: calc(40vw - .85rem)

      &:first-child
        margin-left: -.6rem
</style>
