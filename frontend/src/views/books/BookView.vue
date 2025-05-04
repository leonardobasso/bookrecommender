<script setup lang="ts">
/**
 * Questa View si occupa di mostrare un singolo libro selezionato dall'utente. A seconda dello stato
 * dell'utente, se questo è loggato, sarà possibile inserire le proprie valutazioni
 * @author Leonardo Basso
 */
import { fetchImage } from '@/scripts/crud/fetch-image.ts'
import { onMounted, ref } from 'vue'
import Pill from '@/components/general/Pill.vue'
import { fetchBook } from '@/scripts/crud/fetch-book.ts'
import StarRating from '@/components/books/StarRating.vue'

const imageUrl = ref('')
const bookData = ref('')

async function loadData(title: string) {
  imageUrl.value = await fetchImage(title)
  bookData.value = await fetchBook(title)
}

onMounted(() => {
  loadData('Le migliori barzellette su totti e il calcio')
})
</script>

<template>
  <article class="book_view">
    <div class="book_view__content">
      <section class="book_view__preview">
        <img :src="imageUrl" alt="" class="book_view__cover" />
        <div class="book_view__preview__data">
          <StarRating :rating="4" :readonly="true" />
          <h1 class="book_view__preview__title">{{ bookData.title }}</h1>
          <p>{{ bookData.author }}, {{ bookData.year }}</p>
          <Pill :content="bookData.category" />
          <p class="description">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet assumenda dolore harum
            magni non nostrum repellendus temporibus vero. Alias commodi dolor dolores ea inventore
            laboriosam nam natus rerum saepe tempora.
          </p>
          <p>Costo: {{ bookData.price }}€</p>
        </div>
      </section>
      <h2 class="book_view__subtitles">Gli utenti hanno valutato:</h2>
      <section class="book_view__reviews">
        <div class="book_view__review__content">
          <article class="book_view__review">
            <div class="book_view__review__content">
              <h2 class="book_view__review__vote">1/5</h2>
              <h4 class="book_view__review__category">Gradevolezza</h4>
            </div>
          </article>
        </div>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">2/5</h2>
            <h4 class="book_view__review__category">Stile</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">3/5</h2>
            <h4 class="book_view__review__category">Contenuto</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">4/5</h2>
            <h4 class="book_view__review__category">Originalità</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">5/5</h2>
            <h4 class="book_view__review__category">Edizione</h4>
          </div>
        </article>
      </section>
      <h2 class="book_view__subtitles">Gli utenti hanno consigliato:</h2>
      <StarRating :rating="2" />
    </div>
  </article>
</template>

<style scoped lang="sass">
.book_view
  display: flex
  align-content: center
  justify-content: center

  .book_view__content
    max-width: 95vw
    width: 1000px

    > *
      margin-bottom: 1rem

    @media screen and (max-width: 600px)
      max-width: 80vw

    .book_view__subtitles
      font-size: 1.8rem

    .book_view__preview
      max-height: 750px
      display: flex
      gap: 1rem

      .book_view__preview__data
        > *
          margin-bottom: .6rem

      .book_view__preview__title
        font-size: 2rem

      @media screen and (max-width: 600px)
        flex-direction: column
        max-height: unset

      .book_view__cover
        max-width: 30vw
        width: 700px
        aspect-ratio: 9/16
        border-radius: 1rem
        box-shadow: $standard-shadow
        @media screen and (max-width: 600px)
          max-width: 80vw

    .book_view__reviews
      display: grid
      grid-template-columns: repeat(5, 1fr)
      text-align: center
      margin-top: 1rem
      gap: .6rem
      @media screen and (max-width: 600px)
        grid-template-columns: repeat(1, 1fr)

      .book_view__review
        background-color: rgba($lite, 0.03)
        border: 1px solid rgba($lite, 0.05)
        border-radius: 1rem
        aspect-ratio: 1/1
        display: grid
        align-items: center

        .book_view__review__vote
          font-size: 2.2rem
</style>
