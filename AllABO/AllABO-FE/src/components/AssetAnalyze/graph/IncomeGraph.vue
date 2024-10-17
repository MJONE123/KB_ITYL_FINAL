<template>
  <div
    v-if="isIncomeDataReady"
    class="flex justify-center items-end h-[200px] mt-8 relative space-x-10"
  >
    <!-- 내 연 소득 대비 부채 비율 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-font-color">
        {{ incomeDebtRatio.toFixed(2) }}%
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-pink-5 to-kb-pink-7 hover-grow"
        :style="{ height: `${normalizedUserGraphHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color">나</p>
    </div>

    <!-- '20대 평균' 연 소득 대비 부채 비율 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-gray-700">
        {{ averageIncomeRatio.toFixed(2) }}%
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-pink-5 via-gray-300 to-kb-gray-4"
        :style="{ height: `${normalizedAverageGraphHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color mt-4">20대 평균</p>
    </div>
  </div>

  <div v-else class="text-center">연 소득 그래프 로딩 중...</div>
</template>

<script setup>
import { ref, watch, computed, onMounted } from "vue";

// 부모 컴포넌트에서 전달받은 props
const props = defineProps({
  incomeDebtRatio: {
    type: Number,
    required: true,
  },
  averageIncomeRatio: {
    type: Number,
    required: true,
  },
});

// 각각의 컴포넌트마다 독립적인 상태
const isIncomeDataReady = ref(false);

// 최대값을 기준으로 그래프 높이 비율 계산
const maxGraphHeight = 150; // 그래프의 최대 높이 (px)
const maxRatio = computed(() =>
  Math.max(props.incomeDebtRatio, props.averageIncomeRatio)
);

// 비율 값을 기준으로 그래프 높이를 계산
const normalizedUserGraphHeight = computed(() => {
  return (props.incomeDebtRatio / maxRatio.value) * maxGraphHeight;
});

const normalizedAverageGraphHeight = computed(() => {
  return (props.averageIncomeRatio / maxRatio.value) * maxGraphHeight;
});

// 데이터가 준비되었는지 확인하는 watch
watch(
  [() => props.incomeDebtRatio, () => props.averageIncomeRatio],
  ([newIncomeDebtRatio, newAverageIncomeRatio]) => {
    if (newIncomeDebtRatio && newAverageIncomeRatio) {
      isIncomeDataReady.value = true; // 데이터 준비 완료
      console.log("Data is ready for Income Graph.");
    }
  }
);

// 컴포넌트가 마운트된 후 값이 존재하는지 체크
onMounted(() => {
  if (props.incomeDebtRatio && props.averageIncomeRatio) {
    isIncomeDataReady.value = true;
    console.log("Data is ready on mount for Income Graph.");
  } else {
    console.error("Data is not ready for Income Graph.");
  }
});
</script>
