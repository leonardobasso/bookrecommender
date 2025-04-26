<script setup lang="ts">
import { state } from '@/scripts/state.ts'
</script>

<template>
  <main>
    <center>
      <p v-if="state.user.isLogged">Ciao {{ state.user.userId }}</p>
      <button style="margin-right: 1rem" @click=" state.user.setLogin('myUserID') " >Fake log in</button>
      <button @click=" state.user.setLogOut() " >Fake log out</button> <br> <br>
      <textarea placeholder="Textarea"></textarea>
      <br>
      <input type="text" placeholder="Text Input">
      <button>Button</button>
      <br>
      <label for="ciao">
        <input type="checkbox" name="ciao" id="1">
        <input type="checkbox" name="ciao" id="2">
        <input type="checkbox" name="ciao" id="3">
      </label>
      <h1>Main title</h1>
      <h2>Seconday title</h2>
      <p>Paragraph text Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque magni, maiores odio rem soluta temporibus voluptatibus. Aliquam earum ipsam iure libero maiores molestiae nobis quidem, voluptates? Maxime odio odit temporibus.</p>

    </center>
  </main>
</template>
