<script setup lang="ts">
const props = defineProps<{
  content: string
}>()
</script>

<template>
  <small class="pill">{{ props.content }}</small>
</template>

<style scoped lang="sass">
.pill
  display: inline-block
  padding: .2rem .5rem
  border-radius: 100vh
  background-color: rgba($lite, 0.03)
  border: 1px solid rgba($lite, 0.05)
</style>
