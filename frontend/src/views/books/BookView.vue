<script setup lang="ts">
/**
 * Questa View si occupa di mostrare un singolo libro selezionato dall'utente. A seconda dello stato
 * dell'utente, se questo è loggato, sarà possibile inserire le proprie valutazioni
 * @author Leonardo Basso
 */
import {computed, ref} from 'vue'
import Pill from '@/components/general/Pill.vue'
import {fetchBook} from '@/scripts/crud/fetch-book.ts'
import StarRating from '@/components/books/StarRating.vue'
import {state} from '@/scripts/state.ts'
import {useRoute, useRouter, RouterLink} from 'vue-router';
import {fetchLibraries} from "@/scripts/crud/fetch-libraries.ts";
import {addBookInLibrary} from "@/scripts/crud/add-book-in-library.ts";
import {createSuggestion} from "@/scripts/crud/create-suggestion.ts";
import {fetchSuggestionsByBook} from "@/scripts/crud/fetch-suggestions-by-book.ts";
import {fetchSuggestionsByUser} from "@/scripts/crud/fetch-suggestions-by-user.ts";
import {createReview} from "@/scripts/crud/create-review.ts";
import {fetchReviews} from "@/scripts/crud/fetch-reviews.ts";

const route = useRoute();
const router = useRouter()

//refs
const errorMessage = ref<string | null>(null);
const bookData = ref<any>(null);

const libraries = ref<Array<any>>([])
const libraryDialog = ref<HTMLDialogElement | null>(null)

const valutationDialog = ref<HTMLDialogElement | null>(null)
const adviceDialog = ref<HTMLDialogElement | null>(null)

const suggestedBooks = ref<Array>('')
const mySuggestedBooks = ref<Array>('')
const reviewList = ref<Array>('')

const votoStileRef = ref<number | null>(null)
const votoContenutoRef = ref<number | null>(null)
const votoGradevolezzaRef = ref<number | null>(null)
const votoOriginalitaRef = ref<number | null>(null)
const votoEdizioneRef = ref<number | null>(null)
const votoFinaleRef = ref<number | null>(null)
const commentoStile = ref<string | null>(null)
const commentoContenuto = ref<string | null>(null)
const commentoGradevolezza = ref<string | null>(null)
const commentoOriginalita = ref<string | null>(null)
const commentoEdizione = ref<string | null>(null)
const commentoFinale = ref<string | null>(null)

// Function invocation + ref value declaration
await loadData(route.params.id)

suggestedBooks.value = await fetchSuggestionsByBook(route.params.id)
mySuggestedBooks.value = await fetchSuggestionsByUser(state.user.userId, route.params.id)

let reviews = await fetchReviews(route.params.id)
reviewList.value = reviews.reviews

libraries.value = await fetchLibraries(state.user.userId)

console.log(state.libraryBooks)

// Function implementation

/**
 * Ritorna i valori dei libri
 * @param id
 */
async function loadData(id: string) {
  try {
    const book = await fetchBook(id)
    if (book) {
      bookData.value = book
    } else {
      errorMessage.value = "No data."
    }
  } catch (error) {
    errorMessage.value = error.message
  }
}

async function handleAddToLibrary(libId: string, title: string) {
  await addBookInLibrary(route.params.id, title, libId)
  router.push(`/library/books/${libId}`)
}

/**
 * Questa funzione prende in input una reference a un dialog e lo mostra
 * @param modal - Il dialog da mostrare
 * @author Leonardo Basso
 */
function showDialog(modal: typeof libraryDialog.value) {
  if (modal) {
    modal.showModal()
  }
}

/**
 * Questa funzione prende in input una reference a un dialog e lo nasconde
 * @param modal - Il dialog da nascondere
 * @author Leonardo Basso
 */
function hideDialog(modal: typeof libraryDialog.value) {
  if (modal) {
    modal.close()
  }
}

/**
 * Controlla che il libro sia in libraryBooks, raccolta di tutti i libri salvati in librerie da un utente
 */
const showButtons = ref(false);
showButtons.value = state.libraryBooks.some(book => book.id === route.params.id);

async function handleAddSuggestion(racomendedId: string) {
  try {
    await createSuggestion(state.user.userId, racomendedId, route.params.id)
    suggestedBooks.value = await fetchSuggestionsByBook(route.params.id)
    mySuggestedBooks.value = await fetchSuggestionsByUser(state.user.userId, route.params.id)
  } catch (e) {
    errorMessage.value = e
    throw new Error(e)
  } finally {
    hideDialog(adviceDialog)
  }
}

async function handleNewReview() {
  try {
    const votoStile = votoStileRef.value.getRating();
    const votoContenuto = votoContenutoRef.value.getRating();
    const votoGradevolezza = votoGradevolezzaRef.value.getRating();
    const votoOriginalita = votoOriginalitaRef.value.getRating();
    const votoEdizione = votoEdizioneRef.value.getRating();
    const votoFinale = votoFinaleRef.value.getRating();
    const res = await createReview(state.user.userId, route.params.id, votoStile, votoContenuto,
      votoGradevolezza, votoOriginalita, votoEdizione,
      votoFinale, commentoStile.value, commentoContenuto.value,
      commentoGradevolezza.value, commentoOriginalita.value, commentoEdizione.value,
      commentoFinale.value)
    console.log(res)
    reviews = await fetchReviews(route.params.id)
    reviewList.value = reviews.reviews

  } catch (e) {
    errorMessage.value = e
    throw new Error(e)
  }
}
</script>

<template>
  <article class="book_view">
    <div class="book_view__content">
      <div
        v-if="state.user.isLogged"
        class="book__view__save-btn"
        @click="showDialog(libraryDialog)"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-bookmarks-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M2 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L7 13.101l-4.223 2.815A.5.5 0 0 1 2 15.5z"
          />
          <path
            d="M4.268 1A2 2 0 0 1 6 0h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L13 13.768V2a1 1 0 0 0-1-1z"
          />
        </svg>
      </div>
      <dialog class="book__save" ref="libraryDialog">
        <div class="modal-top">
          <p>Le tue librerie:</p>
          <span class="modal-top__close-btn btn--os" @click="hideDialog(libraryDialog)"></span>
        </div>
        <section v-for="library in libraries" :key="library.id" class="book__save__libraries">
          <div class="book__save__library" @click="handleAddToLibrary(library.id, bookData.title)">

            <p class="book__save__library__name collapsed-text">{{ library.title }}</p>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-plus-circle"
              viewBox="0 0 16 16"
            >
              <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
              <path
                d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"
              />
            </svg>
          </div>
        </section>
      </dialog>
      <div v-if="errorMessage" style="color: #d9534f;"> {{ errorMessage }}</div>
      <section class="book_view__preview" v-if="bookData">
        <img :src="bookData.image" alt="" class="book_view__cover"/>
        <div class="book_view__preview__data">
          <StarRating :rating="reviews.mediaVotoFinale" :readonly="true"/>
          <h1 class="book_view__preview__title">{{ bookData.title }}</h1>
          <p>{{ bookData.author }}, {{ bookData.year }}</p>
          <Pill :content="bookData.category"/>
          <p class="description">{{ bookData.description }}</p>
          <p>Costo: {{ bookData.price }}€</p>
        </div>
      </section>
      <h2 class="book_view__subtitles">Gli utenti hanno valutato:</h2>
      <section class="book_view__reviews">
        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">{{ reviews.mediaGradevolezza }}/5</h2>
            <h4 class="book_view__review__category">Gradevolezza</h4>
          </div>
        </article>


        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">{{ reviews.mediaStile }}/5</h2>
            <h4 class="book_view__review__category">Stile</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">{{ reviews.mediaContenuto }}/5</h2>
            <h4 class="book_view__review__category">Contenuto</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote">{{ reviews.mediaOriginalita }}/5</h2>
            <h4 class="book_view__review__category">Originalità</h4>
          </div>
        </article>

        <article class="book_view__review">
          <div class="book_view__review__content">
            <h2 class="book_view__review__vote"> {{ reviews.mediaEdizione }}/5</h2>
            <h4 class="book_view__review__category">Edizione</h4>
          </div>
        </article>
      </section>

      <button
        v-if="showButtons && state.user.isLogged"
        class="btn--green" @click="showDialog(valutationDialog)"
        style="margin-right: .3rem;">
        Valuta anche tu!
      </button>

      <dialog ref="adviceDialog" class="book__view__advice">
        <div class="modal-top">
          <p>Aggiungi un consigliato</p>
          <span class="modal-top__close-btn btn--os" @click="hideDialog(adviceDialog)"></span>
        </div>

        <section class="book__view__advice__book" v-for="book in state.libraryBooks" :key="book.id">
          <p>{{ book.title }}</p>
          <span @click="handleAddSuggestion(book.id)">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-plus-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
            <path
              d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
          </svg>
          </span>
        </section>
      </dialog>

      <dialog ref="valutationDialog" class="book__view__evaluate">
        <div class="modal-top">
          <p>Valuta il libro</p>
          <span class="modal-top__close-btn btn--os" @click="hideDialog(valutationDialog)"></span>
        </div>
        <label class="book__view__evaluate__label">
          <div class="book__view__evaluate__input">
            <h3>Gradevolezza</h3>
            <StarRating :rating="1" ref="votoGradevolezzaRef"/>
            <textarea placeholder="Gradevolezza" v-model="commentoGradevolezza"></textarea>
          </div>
          <div class="book__view__evaluate__input">
            <h3>Stile</h3>
            <StarRating :rating="1" ref="votoStileRef"/>
            <textarea placeholder="Stile" v-model="commentoStile"></textarea>
          </div>
          <div class="book__view__evaluate__input">
            <h3>Contenuto</h3>
            <StarRating :rating="1" ref="votoContenutoRef"/>
            <textarea placeholder="Contenuto" v-model="commentoContenuto"></textarea>
          </div>
          <div class="book__view__evaluate__input">
            <h3>Originalità</h3>
            <StarRating :rating="1" ref="votoOriginalitaRef"/>
            <textarea placeholder="Originalità" v-model="commentoOriginalita"></textarea>
          </div>
          <div class="book__view__evaluate__input">
            <h3>Edizione</h3>
            <StarRating :rating="1" ref="votoEdizioneRef"/>
            <textarea placeholder="Edizione" v-model="commentoEdizione"></textarea>
          </div>
          <div class="book__view__evaluate__input">
            <h3>Considerazioni finali</h3>
            <StarRating :rating="1" ref="votoFinaleRef"/>
            <textarea placeholder="Commento Finale" v-model="commentoFinale"></textarea>
          </div>
          <button class="btn--green" @click="handleNewReview">Valuta</button>
        </label>
      </dialog>

      <h2 class="book_view__subtitles">Gli utenti hanno consigliato:</h2>
      <p v-if="suggestedBooks.length == 0">Nessun libro consigliato...</p>
      <section class="book_view__suggestions">
        <RouterLink
          v-for="book in suggestedBooks"
          :key="book.id"
          :to="`/books/book/${book.id}`"
        >
          <Pill :content="book.title"/>
        </RouterLink>
      </section>
      <h2 class="book_view__subtitles" v-if="state.user.isLogged">I miei libri consigliati: </h2>
      <p v-if="mySuggestedBooks.length == 0 && state.libraryBooks != null">Non hai consigliato
        nessun
        libro...</p>

      <section v-if="state.user.isLogged && mySuggestedBooks"
               class="book_view__suggestions">
        <button v-if="showButtons && mySuggestedBooks.length < 3" class="btn--green"
                @click="showDialog(adviceDialog);">
          Consiglia un libro!
        </button>

        <RouterLink
          v-for="book in mySuggestedBooks"
          :key="book.id"
          :to="`/books/book/${book.id}`"
        >
          <Pill :content="book.title"/>
        </RouterLink>
      </section>

      <section class="book__view__comments" v-if="reviewList.length > 0">
        <h3 class="book_view__subtitles">Commenti:</h3>
        <section v-for="comment in reviewList" class="book__view__comment">
          <h4>{{ comment.userId }} ha commentato:</h4>
          <article class="book__view__comment__box">
            <div>
              <h4>Stile</h4>
              <p>{{ comment.stileVoto }}/5</p>
            </div>
            <p>{{ comment.stileCommento }}</p>
          </article>

          <article class="book__view__comment__box">
            <div>
              <h4>Contenuto</h4>
              <p>{{ comment.contenutoVoto }}/5</p>
            </div>
            <p>{{ comment.contenutoCommento }}</p>
          </article>

          <article class="book__view__comment__box">
            <div>
              <h4>Originalità</h4>
              <p>{{ comment.originalitaVoto }}/5</p>
            </div>
            <p>{{ comment.originalitaCommento }}</p>
          </article>
          <article class="book__view__comment__box">
            <div>
              <h4>Gradevolezza</h4>
              <p>{{ comment.gradevolezzaVoto }}/5</p>
            </div>
            <p>{{ comment.gradevolezzaCommento }}</p>
          </article>

          <article class="book__view__comment__box">
            <div>
              <h4>Edizione</h4>
              <p>{{ comment.edizioneVoto }}/5</p>
            </div>
            <p>{{ comment.edizioneCommento }}</p>
          </article>

          <article class="book__view__comment__box">
            <div>
              <h4>Voto finale</h4>
              <p>{{ comment.votoFinale }}/5</p>
            </div>
            <p>{{ comment.commentoFinale }}</p>
          </article>
        </section>

      </section>
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

    .book__save
      border-radius: 8px
      padding: .6rem 1rem
      width: 300px
      max-width: 90vw

      .book__save__libraries
        > *
          margin-top: .4rem

      .book__save__library
        display: flex
        justify-content: space-between
        align-items: center

        svg
          cursor: pointer

    .book__view__save-btn
      position: absolute
      top: 4rem
      right: 2rem
      cursor: pointer

      svg
        scale: 1.3

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
        grid-template-columns: repeat(2, 1fr)

      .book_view__review
        width: 100%
        background-color: rgba($lite, 0.03)
        border: 1px solid rgba($lite, 0.05)
        border-radius: 1rem
        aspect-ratio: 1/1
        display: grid
        align-items: center

        .book_view__review__vote
          font-size: 2.2rem

  .book__view__evaluate
    width: 90vw
    height: 90vh

    .book__view__evaluate__label
      display: grid
      gap: 1rem
      align-items: center
      justify-content: center

      .book__view__evaluate__input
        textarea
          background-color: rgba($lite, 0.02)
          resize: none

  .book__view__advice__book
    display: flex
    gap: 1rem
    align-items: center
    justify-content: space-between
    margin-bottom: .5rem

    svg
      cursor: pointer

  .book_view__suggestions
    *
      margin-left: .1rem

  .book__view__comments
    .book__view__comment
      @include default-box
      border-radius: 1rem
      padding: .5rem
      margin-bottom: .5rem

      .book__view__comment__box
        margin-top: .3rem
        display: flex
        gap: 1rem
        border-bottom: 1px solid rgba($lite, 0.05)

.modal-top
  display: flex
  justify-content: space-between
  align-items: center
  margin-bottom: .8rem

  .modal-top__close-btn
    background-color: $light-red

.svg-green
  svg
    fill: $light-green
</style>
