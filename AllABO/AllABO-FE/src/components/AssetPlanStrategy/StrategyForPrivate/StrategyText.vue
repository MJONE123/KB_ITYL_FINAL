<template>
  <h1 class="text-[36px] whitespace-nowrap">
    월 지출을 {{ percent }}% {{ isDeclineText }}하여<br />
    <span class="text-red-500 text-[44px] my-3 block"
      >{{ strategy }}<br
    /></span>
    을(를) 추천합니다.
  </h1>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  percent: {
    type: Number,
    required: true,
  },
  isDecline: {
    type: Boolean,
    required: true,
  },
  strategy: {
    type: String,
    required: true,
  },
});

const isDeclineText = computed(() => {
  return props.isDecline ? '감소' : '증가';
});
</script>
