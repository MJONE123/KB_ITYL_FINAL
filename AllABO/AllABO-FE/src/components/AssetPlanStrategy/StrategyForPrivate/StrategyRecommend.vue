<template>
  <div>
    <h1 class="text-[44px]">{{ title }}</h1>

    <div class="flex gap-5 mt-5">
      <span
        class="bg-kb-brown-3 rounded-[100px] p-2 text-center text-white w-[180px]"
        v-for="(product, index) in products"
        :key="index"
        >{{ product }}</span
      >
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  savingStrategy: {
    type: Object,
    required: false,
  },
  investmentStrategy: {
    type: Object,
    required: false,
  },
});

const title = computed(() =>
  props.savingStrategy ? '권장 저축 상품' : '권장 투자 상품'
);

const products = computed(() =>
  props.savingStrategy
    ? props.savingStrategy['권장_저축_상품(최대3개)']
    : props.investmentStrategy['권장_투자_상품']
);
</script>
