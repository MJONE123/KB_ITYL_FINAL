<template>
  <div>
    <div
      class="rounded-full h-[300px] w-[300px] bg-kb-yellow-3 relative flex justify-center items-center"
    >
      <div class="flex flex-col items-center">
        <span class="text-[24px] mt-3">{{ titleText }}</span>
        <span class="text-[45px] text-red-500 font-bold">{{
          percentText
        }}</span>
      </div>

      <!-- border circle -->
      <div
        class="absolute w-full h-full translate-x-2 border rounded-full top-[-4px] right-[-4px] border-kb-yellow-2"
      ></div>
      <div
        class="absolute w-full h-full border rounded-full border-kb-yellow-2 top-2 right-2"
      ></div>
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
  data: {
    type: Object,
    required: false,
  },
  text: {
    type: String,
    required: false,
  },
});

const titleText = computed(() => {
  if (props.title === '지출_조정') return '조정 지출 비율';
  if (props.title === '저축_전략') return '목표 저축률';
  if (props.title === '투자_전략') return '목표 투자 비율';
  return props.title;
});

const percentText = computed(() => {
  if (props.data) {
    const isSpending = Object.keys(props.data).includes('월_지출');
    const isSaving = Object.keys(props.data).includes('목표_저축률');
    const isInvestment = Object.keys(props.data).includes('목표_투자_비율');

    if (isSpending) return props.data['월_지출'] + '%';
    if (isSaving) return props.data['목표_저축률'] + '%';
    if (isInvestment) return props.data['목표_투자_비율'] + '%';
  } else {
    return props.text;
  }
});
</script>
