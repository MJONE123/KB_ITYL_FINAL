<template>
  <button
    @click="$emit('click')"
    :class="[
      'rounded-[15px] px-4 py-3 duration-300',
      left
        ? 'text-white border border-white hover:bg-white hover:text-kb-brown-2'
        : 'bg-white text-kb-brown-2 shadow-md hover:bg-kb-brown-2 hover:text-white',
    ]"
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
  left: {
    type: Boolean,
    required: false,
  },
  url: {
    type: String,
    required: false,
  },
});

defineEmits(['click']);
</script>
