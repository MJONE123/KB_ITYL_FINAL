<template>
  <div
    :class="[
      'solution-item',
      positionClass,
      { 'solution-visible': isVisible },
      'absolute w-96 h-32 bg-white rounded-lg shadow-md flex flex-col overflow-hidden',
    ]"
    :style="{ animationDelay: animationDelay }"
  >
    <div class="bg-kb-yellow-8 p-2 text-center">
      <span class="text-lg text-gray-800">{{ title.replace(/_/g, ' ') }}</span>
    </div>
    <div class="flex-grow flex items-center justify-center p-4">
      <span class="text-base text-gray-600 text-center">{{ content }}</span>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  content: {
    type: String,
    required: true,
  },
  positionClass: {
    type: String,
    required: true,
  },
  isVisible: {
    type: Boolean,
    required: true,
  },
  animationDelay: {
    type: String,
    required: true,
  },
});
</script>

<style scoped>
.solution-item {
  opacity: 0;
  transform: scale(0.8);
  transition: opacity 0.5s, transform 0.5s;
}

.solution-visible {
  opacity: 1;
  transform: scale(1);
}
</style>
