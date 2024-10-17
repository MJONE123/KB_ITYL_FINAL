<template>
  <div
    class="absolute top-1/2 left-4 transform -translate-y-1/2"
    v-if="currentIndex > 0"
  >
    <button @click="$emit('prev')" class="text-kb-yellow-11 p-2 rounded-full">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="size-14 hover:size-16 duration-200"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="m11.25 9-3 3m0 0 3 3m-3-3h7.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
        />
      </svg>
    </button>
  </div>
  <div
    class="absolute top-1/2 right-4 transform -translate-y-1/2"
    v-if="currentIndex < 2"
  >
    <button @click="$emit('next')" class="text-kb-yellow-11 p-2 rounded-full">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="size-14 hover:size-16 duration-200"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="m12.75 15 3-3m0 0-3-3m3 3h-7.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
        />
      </svg>
    </button>
  </div>
</template>

<script setup>
defineProps({
  currentIndex: {
    type: Number,
    required: true,
  },
});

defineEmits(['prev', 'next']);
</script>

<style></style>
