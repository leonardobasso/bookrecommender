<script setup lang="ts">
import { ref } from 'vue';

const props = defineProps({
  rating: {
    type: Number,
    default: 0,
    validator: (value: number) => value >= 0 && value <= 5
  },
  readonly: {
    type: Boolean,
    default: false
  },
});

const rating = ref(props.rating);

/**
 *  Funzione per impostare il rating quando si clicca su una stella se il componente non è in modalità di
 *  sola lettura
 * @param value - Il valore da impostare
 */
function setRating(value: number) {
  if (!props.readonly) {
    rating.value = value;
  }
}

/**
 * Getter per il valore della votazione
 */
function getRating(): number {
  return rating.value;
}

defineExpose({
  getRating,
});
</script>

<template>
  <div class="star-rating">
    <span
      v-for="star in 5"
      :key="star"
      :class="['star', { 'star-filled': star <= rating }]"
      @click="setRating(star)"
      :style="{ cursor: props.readonly ? 'default' : 'pointer' }"
    >
      ★
    </span>
  </div>
</template>

<style scoped lang="sass">
.star-rating
  display: flex
  gap: 0.2rem

.star
  font-size: 2rem
  color: #ccc

.star-filled
  color: gold
</style>
