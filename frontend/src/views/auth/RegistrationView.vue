<script setup lang="ts">
/**
 * Questa View si occupa di gestire la registrazione dell'utente
 * @author Leonardo Basso
 */
import { RouterLink, useRouter } from 'vue-router'
import {state} from "@/scripts/state.ts";
import {ref} from "vue";
import {register} from "@/scripts/crud/user.ts";

const router = useRouter()
const errorMessage = ref('')
const userId = ref('')
const name = ref('')
const surname = ref('')
const password = ref('')
const email = ref('')
const taxcode = ref('' +
  '')
async function handleRegister() {
  errorMessage.value = ''
  try {
    const userData = await register(userId.value, password.value, name.value, surname.value, taxcode.value, email.value)
    if (userData.userID != null) {
      state.user.setLogin(
        userData.userID,
        userData.nome,
        userData.cognome,
        userData.email,
        userData.taxcode
      )
      await router.push('/')

    } else {
      errorMessage.value = userData
    }
  } catch (e: any) {
    errorMessage.value = e.message
  }
}

</script>

<template>
  <section class="register">
    <p v-if="state.user.isLogged"> Sei già loggato come <u>{{ state.user.userId }}</u></p>
    <div v-if="!state.user.isLogged" class="register__content">
      <div class="register__double_input">
        <input v-model="name" type="text" placeholder="Nome" />
        <input v-model="surname" type="text" placeholder="Cognome" />
      </div>
      <div class="register__double_input">
        <input v-model="userId" type="text" placeholder="UserID" />
        <input v-model="taxcode" type="text" placeholder="Codice Fiscale" />
      </div>
      <input v-model="email" class="register__single_input" type="email" placeholder="Email" />
      <input v-model="password" class="register__single_input" type="password" placeholder="Password" />
      <button @click="handleRegister">Register</button>
      <p v-if="errorMessage" style="color: #d9534f;">{{ errorMessage }}</p>
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
