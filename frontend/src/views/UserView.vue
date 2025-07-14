<script setup lang="ts">
import {state} from '@/scripts/state.ts'
import {ref} from "vue";
import {fetchLibraries} from "@/scripts/crud/fetch-libraries.ts";
import {login} from "@/scripts/crud/user.ts";
import {createLibrary} from "@/scripts/crud/create-library.ts";

const modal = ref<HTMLDialogElement | null>(null)
const libraries = ref<Array<any>>([]);
const libName = ref('')
const errorMessage = ref('')

libraries.value = await fetchLibraries(state.user.userId)

async function handleNewLib() {
  const newLib = await createLibrary(state.user.userId, libName.value)
  libraries.value = await fetchLibraries(state.user.userId)
  if (newLib != "success") {
    errorMessage.value = "Errore nell'inserimento, probabile nome doppio"
  } else {
    errorMessage.value = ''
  }
  hideDialog()

}

/**
 * Questa funzione prende in input una reference a un dialog e lo mostra
 * @author Leonardo Basso
 */

function showDialog() {
  if (modal.value) {
    modal.value.showModal()
  }
}

/**
 * Questa funzione prende in input una reference a un dialog e lo nasconde
 * @author Leonardo Basso
 */
function hideDialog() {
  if (modal.value) {
    modal.value.close()
  }
}

</script>

<template>
  <section class="user__view" v-if="state.user.isLogged">
    <header class="user__view__top">
      <button @click="state.user.setLogOut()" class="user__view__logout btn--red">
        Log out
      </button>
    </header>
    <div class="user__view__content">
      <section class="user__view__list">
        <p v-if="errorMessage" style="color: #d9534f;">{{ errorMessage }}</p>
        <div class="user__view__list__top">
          <h2>Le mie librerie</h2>
          <div class="user__view__list__add-btn" @click="showDialog()">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-plus-circle" viewBox="0 0 16 16">
              <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
              <path
                d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
            </svg>
          </div>
        </div>
        <div class="user__view__elements" v-for="library in libraries">
          <RouterLink :to="`/library/books/${library.id}`">
            <p class="user__view__el collapsed-text">{{ library.title }}</p>
          </RouterLink>
        </div>
        <dialog class="user__view__add__library" ref="modal">
          <div class="user__view__add__library__top">
            <p>Aggiungi una libreria</p>
            <span class="user__view__close-btn btn--os" @click="hideDialog()"></span>
          </div>
          <label class="user__view__add__library__input">
            <input v-model="libName" type="text" placeholder="Nome Libreria">
            <button class="btn--green" @click="handleNewLib">Aggiungi</button>
          </label>
        </dialog>
      </section>
      <section class="user__view__list user__view__list__infos">
        <div class="user__view__list__top">
          <h2>Le mie info</h2>
        </div>
        <div class="user__view__elements">
          <p class="user__view__el collapsed-text"><strong>UserID: </strong> {{ state.user.userId }}
          </p>
          <p class="user__view__el collapsed-text"><strong>Nome: </strong> {{ state.user.name }}
            {{ state.user.surname }}</p>
          <p class="user__view__el collapsed-text"><strong>Email: </strong> {{ state.user.email }}
          </p>
          <p class="user__view__el collapsed-text"><strong>Codice Fiscale: </strong>
            {{ state.user.taxcode }}</p>
        </div>
      </section>
    </div>
  </section>
  <div class="not-logged" v-if="state.user.isLogged == false">
    <p>
      <RouterLink to="/">Torna alla homepage</RouterLink>
    </p>
  </div>
</template>

<style scoped lang="sass">
.user__view
  .user__view__top
    display: flex
    justify-content: space-between
    margin-bottom: .5rem

  .user__view__content
    display: flex
    flex-wrap: wrap
    justify-content: center
    gap: 1rem
    width: 100%
    max-width: 1200px
    margin: 0 auto

    .user__view__list
      @include default-box
      flex: 1 1 450px
      max-width: 700px
      padding: .3rem .8rem
      border-radius: .5rem
      box-sizing: border-box

      .user__view__list__top
        margin-bottom: .3rem
        padding-bottom: .3rem
        border-bottom: 1px solid rgba($lite, .03)
        display: flex
        justify-content: space-between
        align-items: center

        .user__view__list__add-btn
          scale: 1.2
          cursor: pointer

          svg
            fill: $light-green

      .user__view__elements
        display: flex
        flex-direction: column
        gap: .5rem

      .user__view__el
        cursor: pointer
        margin: 0
        word-break: break-word

  .user__view__list__infos
    height: 170px

  .user__view__add__library
    .user__view__add__library__top
      display: flex
      justify-content: space-between
      align-items: center
      margin-bottom: 1rem

      .user__view__close-btn
        background-color: $light-red

    .user__view__add__library__input
      display: flex
      gap: .3rem

.not-logged
  p, a
    color: $light-red
    position: absolute
    top: 50%
    left: 50%
    transform: translate(-50%, -50%)
</style>
