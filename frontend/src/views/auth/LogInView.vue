<script setup lang="ts">
/**
 * Questa View si occupa di gestire il Log In dell'utente
 * @author Leonardo Basso
 */
import {RouterLink, useRouter} from 'vue-router'
import {ref} from 'vue'
import {state} from '@/scripts/state.ts'
import {login} from "@/scripts/crud/user.ts";

const router = useRouter()
const userId = ref('')
const password = ref('')
const errorMessage = ref('')
async function handleLogin() {
  errorMessage.value = ''
  try {
    const userData = await login(userId.value, password.value)
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
  <p v-if="state.user.isLogged"> Sei già loggato come <u>{{ state.user.userId }}</u></p>
  <section v-if="!state.user.isLogged" class="register">
    <input v-model="userId" type="text" placeholder="UserID"/>
    <input v-model="password" type="password" placeholder="Password..."/>
    <button @click="handleLogin">Log In</button>
    <p v-if="errorMessage" style="color: #d9534f;">{{ errorMessage }}</p>
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
