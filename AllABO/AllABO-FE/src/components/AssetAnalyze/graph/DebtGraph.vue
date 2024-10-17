<template>
  <div
    v-if="isDebtDataReady"
    class="flex justify-center items-end h-[200px] mt-8 relative space-x-10"
  >
    <!-- 내 자산 대비 부채율 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-font-color">
        {{ assetDebtRatio.toFixed(2) }}%
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-yellow-4 to-kb-yellow-7 hover-grow"
        :style="{ height: `${normalizedUserDebtHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color">나</p>
    </div>

    <!-- '20대 평균' 자산 대비 부채율 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-gray-700">
        {{ averageDebtRatio.toFixed(2) }}%
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-yellow-8 via-gray-300 to-kb-gray-4"
        :style="{ height: `${normalizedAverageDebtHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color mt-4">20대 평균</p>
    </div>
  </div>

  <div v-else class="text-center">그래프 로딩 중...</div>
</template>

<script setup>
import { ref, watch, computed, onMounted } from "vue";

// 부모 컴포넌트에서 전달받은 props
const props = defineProps({
  assetDebtRatio: {
    type: Number,
    required: true,
  },
  averageDebtRatio: {
    type: Number,
    required: true,
  },
});

// 각각의 컴포넌트마다 독립적인 상태
const isDebtDataReady = ref(false);

// 그래프 높이 비율 계산 (최대 높이를 150px로 설정)
const maxGraphHeight = 150; // 그래프의 최대 높이 (px)
const maxDebtRatio = computed(() =>
  Math.max(props.assetDebtRatio, props.averageDebtRatio)
);

// 비율 값을 기준으로 그래프 높이를 계산
const normalizedUserDebtHeight = computed(() => {
  return (props.assetDebtRatio / maxDebtRatio.value) * maxGraphHeight;
});

const normalizedAverageDebtHeight = computed(() => {
  return (props.averageDebtRatio / maxDebtRatio.value) * maxGraphHeight;
});

// 데이터가 준비되었는지 확인하는 watch
watch(
  [() => props.assetDebtRatio, () => props.averageDebtRatio],
  ([newAssetDebtRatio, newAverageDebtRatio]) => {
    if (newAssetDebtRatio && newAverageDebtRatio) {
      isDebtDataReady.value = true; // 데이터 준비 완료
      console.log("Data is ready for Debt Graph.");
    }
  }
);

// 컴포넌트가 마운트된 후 값이 존재하는지 체크
onMounted(() => {
  if (props.assetDebtRatio && props.averageDebtRatio) {
    isDebtDataReady.value = true;
    console.log("Data is ready on mount for Debt Graph.");
  } else {
    console.error("Data is not ready for Debt Graph.");
  }
});
</script>
