<script setup lang="ts">
/**
 * View per la visione dei dettagli di una libreria dato un id
 */
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import { fetchLibraryDetails } from '@/scripts/crud/fetch-library-details.ts'

const route = useRoute()
const books = ref<Array<any>>([])
const libraryId = route.params.id as string

books.value = await fetchLibraryDetails(libraryId)
</script>

<template>
  <main class="librarypage">
    <h2 class="librarypage__title">Libri della libreria {{ libraryId }}</h2>

    <section class="librarypage__book__list">
      <div class="librarypage__book__list__content">
        <article class="librarypage__book" v-for="book in books" :key="book.id">
          <RouterLink :to="`/books/book/${book.id}`">
            <p class="collapsed-text">{{ book.title }}</p>
          </RouterLink>
          <p class="collapsed-text">{{ book.author }}</p>
          <p class="collapsed-text">{{ book.year }}</p>
          <p class="collapsed-text">{{ book.category }}</p>
        </article>
      </div>
    </section>
  </main>
</template>

<style scoped lang="sass">
.librarypage
  text-align: center
  padding: 1rem

  .librarypage__title
    font-size: 1.5rem
    margin-bottom: 1rem

  .librarypage__book__list
    display: grid
    justify-content: center

    .librarypage__book__list__content
      @include default-box
      width: 90vw
      border-radius: .5rem
      text-align: left

      .librarypage__book
        display: grid
        grid-template-columns: 3fr 2fr 0.5fr .8fr
        gap: .5rem
        padding: .5rem .8rem

        &:nth-child(even)
          border-bottom: 1px solid rgba($lite, 0.02)
          border-top: 1px solid rgba($lite, 0.02)
</style>
