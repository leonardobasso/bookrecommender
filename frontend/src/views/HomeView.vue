<script setup lang="ts">
import { state } from '@/scripts/state.ts'
import Pill from "@/components/general/Pill.vue";

// Dati di esempio per i libri
const books = [
  { title: 'Le barzellette di Totti', author: 'Francesco Totti', year: 2019, category: 'Filosofia' },
  { title: 'Un libro da un titolo davvero, ma davvero taaaaaanto luuuuuuuuuuungo', author: 'Autore Sconosciuto', year: 2020, category: 'Romanzo' },
  { title: 'Altro Libro', author: 'Autore Sconosciuto', year: 2020, category: 'Romanzo' },
  { title: 'Altro Libro', author: 'Autore Sconosciuto', year: 2020, category: 'Romanzo' },
  { title: 'Altro Libro', author: 'Autore Sconosciuto', year: 2020, category: 'Romanzo' },
  // Aggiungi altri libri qui
]
</script>

<template>
  <main class="homepage">
    <p v-if="state.user.isLogged">Ciao {{ state.user.userId }}</p>
    <button style="margin-right: 1rem" @click="state.user.setLogin('myUserID')" class="btn--green">Fake log in</button>
    <button @click="state.user.setLogOut()" class="btn--red">Fake log out</button> <br> <br>
    <br>
    <label for="" class="homepage__search">
      <input type="text" placeholder="Text Input">
      <button>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
        </svg>
      </button>
    </label>

    <section class="homepage__book__list">
      <div class="homepage__book__list__content">
        <article class="homepage__book" v-for="book in books" :key="book.title">
          <p class="collapsed-text">{{ book.title }}</p>
          <p class="collapsed-text">{{ book.author }}</p>
          <p class="collapsed-text">{{ book.year }}</p>
          <p class="collapsed-text"> {{book.category}}</p>
        </article>
      </div>
    </section>
  </main>
</template>

<style scoped lang="sass">
.homepage
  text-align: center

  .homepage__search
    input
      width: 80vw
      max-width: 500px
      height: 2.5rem

    button
      height: 2.5rem
      aspect-ratio: 1/1

    >*
      margin-left: .5rem

  .homepage__book__list
    display: grid
    justify-content: center
    margin-top: 1rem

    .homepage__book__list__content
      @include default-box
      width: 90vw
      border-radius: .5rem
      text-align: left

      .homepage__book
        display: grid
        grid-template-columns: 3fr 2fr 0.5fr .8fr
        gap: .5rem
        padding: .5rem .8rem

        &:nth-child(even)
          border-bottom: 1px solid rgba($lite, 0.02)
          border-top: 1px solid rgba($lite, 0.02)

        .collapsed-text
          margin: 0
          text-align: left
          word-break: break-word
</style>
