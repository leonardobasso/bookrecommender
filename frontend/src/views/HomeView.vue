<script setup lang="ts">
import { state } from '@/scripts/state.ts'
import {fetchImage} from "@/scripts/crud/fetch-image.ts";
import {onMounted, ref} from "vue";


const imageUrl = ref('');

async function loadImage(title) {
  imageUrl.value = await fetchImage(title);
}

onMounted(() => {
  loadImage('Barzellette Totti');
});

</script>

<template>
  <main>
    <center>
      <p v-if="state.user.isLogged">Ciao {{ state.user.userId }}</p>
      <button style="margin-right: 1rem" @click=" state.user.setLogin('myUserID') " class="btn--green">Fake log in</button>
      <button @click=" state.user.setLogOut() " class="btn--red">Fake log out</button> <br> <br>
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
      <img :src="imageUrl" alt="">
    </center>
  </main>
</template>
