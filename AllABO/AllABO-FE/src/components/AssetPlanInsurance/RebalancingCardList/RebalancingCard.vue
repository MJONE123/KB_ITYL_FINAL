<template>
  <div
    class="relative bg-[#FFF7CD] rounded-2xl shadow-md w-[280px] h-[380px] overflow-hidden"
  >
    <div
      class="absolute top-0 left-0 right-0 h-1/3 flex items-center justify-center"
    >
      <h2 class="text-lg text-center font-bold text-font-color">
        {{ title.replace(/_/g, ' ') }}
      </h2>
    </div>

    <div
      class="absolute top-[90px] bottom-0 left-0 right-0 flex flex-col items-left p-5"
    >
      <p
        v-for="(item, index) in content"
        :key="index"
        class="bg-kb-yellow-9 rounded-lg p-1 my-1 text-center shadow-md"
      >
        {{ item }}
      </p>
      <p class="mt-8" v-html="text"></p>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  title: String,
  content: {
    type: Array,
    required: true,
  },
  isMaintain: {
    type: String,
    required: true,
  },
});

const text = computed(() => {
  if (props.isMaintain === '부분 유지') {
    return `필수적이진 않지만, <strong>유지를 고려</strong>해 보세요. 보장 내용과 보험료 최적화 여부를 검토해보시기 바랍니다.`;
  }

  if (props.isMaintain === '필수 유지') {
    return '귀하의 위험을 효과적으로 관리하므로 <strong>유지를 강력히 권장</strong>합니다. 주기적으로 적합성을 확인하세요.';
  }

  if (props.isMaintain === '조정 고려') {
    return '보장, 보험료, 보험금 등의 <strong>조정이 필요</strong>해 보입니다. 전문가와 상담하여 최적의 조정 방안을 찾아보세요.';
  }
});
</script>
