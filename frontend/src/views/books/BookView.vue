<script setup lang="ts">
import { fetchImage } from '@/scripts/crud/fetch-image.ts'
import { onMounted, ref } from 'vue'
import Pill from "@/components/general/Pill.vue";
import {fetchBook} from "@/scripts/crud/fetch-book.ts";

const imageUrl = ref('')
const bookData = ref('')

async function loadData(title: string) {
  imageUrl.value = await fetchImage(title)
  bookData.value = await fetchBook(title)
}

onMounted(() => {
  loadData('Barzellette Totti')
})
</script>

<template>
  <section class="book_view">
    <div class="book_view__content">
      <div class="book_view__preview">
        <img :src="imageUrl" alt="" class="book_view__cover" />
        <div class="book_view__preview__data">
          <h1 class="title">{{bookData.title}}</h1>
          <p>{{bookData.author}}, {{bookData.year}}</p>
          <Pill :content="bookData.category" />
          <p class="description">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet assumenda dolore harum
            magni non nostrum repellendus temporibus vero. Alias commodi dolor dolores ea inventore
            laboriosam nam natus rerum saepe tempora.
          </p>
          <p>Costo: {{bookData.price}}€</p>
        </div>
      </div>
      <p>Altro contenuto</p>
    </div>
  </section>
</template>

<style scoped lang="sass">
.book_view
  display: flex
  align-content: center
  justify-content: center

  .book_view__content
    max-width: 80vw
    width: 1000px

    .book_view__preview
      max-height: 550px
      display: flex
      gap: 1rem
      @media screen and (max-width: 600px)
        flex-direction: column
        max-height: unset

      .book_view__cover
        max-width: 80vw
        width: 700px
        aspect-ratio: 9/16
        border-radius: 1rem
</style>
