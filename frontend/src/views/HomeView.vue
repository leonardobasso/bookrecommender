<script setup lang="ts">
import {state} from '@/scripts/state.ts'
import {fetchBooks} from "@/scripts/crud/fetch-books.ts";
import {searchBooks} from "@/scripts/crud/search-books.ts";
import {ref} from 'vue'

const searchTitle = ref('')
const searchAuthor = ref('')
const searchYear = ref('')
const books = ref<Array<any>>([]);

books.value = await fetchBooks()
console.log(books.value)

/**
 * Modifica dinamicamente i dati mostrati data una ricerca
 * @author Leonardo Basso
 */
async function handleSearch() {
  try {
    books.value = await searchBooks(searchTitle.value, searchAuthor.value, searchYear.value)
  } catch (error) {
    console.error("Errore durante la ricerca:", error)
  }
}
</script>

<template>
  <main class="homepage">
    <p v-if="state.user.isLogged">Ciao {{ state.user.userId }}</p>
    <button style="margin-right: 1rem" @click="state.user.setLogin('myUserID')" class="btn--green">
      Fake log in
    </button>
    <button @click="state.user.setLogOut()" class="btn--red">Fake log out</button>
    <br> <br>
    <br>
    <label for="" class="homepage__search">
      <span class="search__section">
        <input v-model="searchTitle" type="text" placeholder="Titolo" id="search-title">
        <button @click="handleSearch">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-search" viewBox="0 0 16 16">
            <path
              d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
          </svg>
        </button>
      </span>
      <span class="search__section">
        <input v-model="searchAuthor" type="text" placeholder="Autore" id="search-author">
        <input v-model="searchYear" type="text" placeholder="Anno" id="search-year">
      </span>
    </label>

    <section class="homepage__book__list">
      <div class="homepage__book__list__content">
        <article class="homepage__book" v-for="book in books" :key="book.id">
          <RouterLink :to="`/books/book/${book.id}`" :id="book.id">
            <p class="collapsed-text">{{ book.title }}</p>
          </RouterLink>
          <p class="collapsed-text">{{book.author}}</p>
          <p class="collapsed-text">{{ book.year }}</p>
          <p class="collapsed-text">{{book.category}}</p>
        </article>
      </div>
    </section>
  </main>
</template>

<style scoped lang="sass">
.homepage
  text-align: center

  .homepage__search
    display: flex
    flex-direction: column
    align-items: center
    margin-bottom: 1rem

    .search__section
      display: flex
      gap: .5rem
      margin-bottom: .5rem
      justify-content: center
      width: 100%
      max-width: 700px

      input
        flex: 1
        padding: 0.5rem
        font-size: 1rem

      button
        height: 2.5rem
        aspect-ratio: 1 / 1
        display: flex
        align-items: center
        justify-content: center

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
