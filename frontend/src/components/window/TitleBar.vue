<script setup lang="ts">
/**
 * Component che si occupa di creare la titlebar e gestisce le sue funzionalità
 * @author Leonardo Basso
 */
import { getCurrentWindow } from '@tauri-apps/api/window'

const appWindow = getCurrentWindow()
</script>

<template>
  <header data-tauri-drag-region class="titlebar">
    <p class="titlebar__title">Book Recommender</p>
    <nav class="titlebar__buttons">
      <div class="titlebar__button" @click="appWindow.minimize()">
        <span class="titlebar__circle minimize"></span>
      </div>
      <div class="titlebar__button" @click="appWindow.toggleMaximize()">
        <span class="titlebar__circle maximize"></span>
      </div>
      <div class="titlebar__button">
        <span class="titlebar__circle close" @click="appWindow.close()"></span>
      </div>
    </nav>
  </header>
</template>
<style scoped lang="sass">
.titlebar
  position: fixed
  height: 2rem
  border-radius: .5rem .5rem 0 0
  background-color: $dark-gray
  display: flex
  width: 100vw
  align-items: center
  justify-content: space-between

  > *
    margin: 0 1rem

  .titlebar__title
    user-select: none
    -webkit-user-select: none

  .titlebar__buttons
    display: flex
    gap: .3rem

    // Presenti per dare una porzione cliccabile maggiore di quella visibile, andando a rendere piu
    // facile le operazioni di chiusura, minimizzazione e massimizzazione della finestra
    .titlebar__button
      display: flex
      padding: .1rem

    .titlebar__circle
      width: .8rem
      height: .8rem
      border-radius: 100vh
      cursor: pointer

    .minimize
      background-color: $light-blue

    .maximize
      background-color: $light-green

    .close
      background-color: $light-red
</style>
