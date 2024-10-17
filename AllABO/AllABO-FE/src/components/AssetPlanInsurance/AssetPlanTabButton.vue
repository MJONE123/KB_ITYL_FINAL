<template>
<div
        class="w-[240px] bg-[#F9E26F] p-1 mb-2 rounded-full shadow-md flex items-center justify-center space-x-2"
      >
      <button
        @click="$emit('update:modelValue', true)"
        :class="[
          'px-4 py-2 rounded-full text-sm font-medium transition-all duration-200',
          modelValue
            ? 'bg-[#987139] text-white'
            : ' hover:bg-yellow-200',
        ]"
      >
        현재 보험 분석
      </button>
      <button
        @click="$emit('update:modelValue', false)"
        :class="[
          'px-4 py-2 rounded-full text-sm font-medium transition-all duration-200',
          !modelValue
            ? 'bg-[#987139] text-white'
            : ' hover:bg-yellow-200',
        ]"
      >
        리밸런싱 제안
      </button>
      </div>
</template>

<script setup>
defineProps({
    modelValue: {
        type: Boolean,
        required: true,
    }
});

</script>

<style>
    
</style>