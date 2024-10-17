<template>
  <button
    class="rounded-full flex items-center justify-center transition-all duration-200 ease-in-out hover:scale-110 box-shadow"
    :style="{
      width: '100%',
      height: '100%',
      backgroundColor: backgroundColor,
    }"
    @mouseenter="$emit('mouseenter')"
    @mouseleave="$emit('mouseleave')"
  >
    <p
      class="text-font-color text-center font-bold text-2xl transition-all duration-200 ease-in-out group-hover:scale-110"
    >
      {{ categoryName }}
    </p>
  </button>
</template>

<script setup>
const props = defineProps({
  categoryName: {
    type: String,
    required: true,
  },
  backgroundColor: {
    type: String,
    required: true,
  },
});

defineEmits(['mouseenter', 'mouseleave']);
</script>

<style scoped>
.text-shadow {
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.box-shadow {
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}
</style>
