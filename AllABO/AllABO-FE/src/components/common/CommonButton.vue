<template>
  <button
    class="text-font-color bg-asset-yellow-2 w-32 hover:bg-[#FFF1BF] duration-300 shadow-md rounded-[15px] py-2 px-4"
  >
    {{ text }}
  </button>
</template>

<script setup>
const props = defineProps({
  text: {
    type: String,
    required: true,
  },
});
</script>
