<template>
  <div
    v-if="isTotalDataReady"
    class="flex justify-center items-end h-[300px] space-x-10"
  >
    <!-- 내 자산 총액 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-font-color">
        {{ formatNumber(userTotalAssets) }}만원
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-blue-6 to-kb-blue-5 hover-grow"
        :style="{ height: `${normalizedUserHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color">나</p>
    </div>

    <!-- '20대 평균' 자산총액 그래프 -->
    <div class="flex flex-col items-center">
      <p class="text-center text-[14px] text-font-color">
        {{ formatNumber(averageTotalAssets) }}만원
      </p>
      <div
        class="w-[70px] rounded-t-lg bg-gradient-to-t from-kb-blue-6 via-gray-300 to-kb-gray-4"
        :style="{ height: `${normalizedAverageHeight}px` }"
      ></div>
      <p class="text-center text-[12px] text-font-color">20대 평균</p>
    </div>
  </div>

  <div v-else class="text-center">총 자산 그래프 로딩 중...</div>
</template>

<script setup>
import { ref, watch, computed, onMounted } from "vue";

// Props로 받은 자산 값
const props = defineProps({
  userTotalAssets: {
    type: Number,
    required: true,
  },
  averageTotalAssets: {
    type: Number,
    required: true,
  },
});

//포맷함수
const formatNumber = (value) => {
  if (typeof value === "number") {
    return Math.floor(value).toLocaleString(); // 소수점 이하 버리고 세 자리마다 콤마 추가
  }
  return value;
};

// 각각의 컴포넌트마다 독립적인 상태
const isTotalDataReady = ref(false);

// 최대값을 기준으로 그래프 높이 비율 계산
const maxGraphHeight = 150; // 그래프의 최대 높이 (px)
const maxAssets = computed(() =>
  Math.max(props.userTotalAssets, props.averageTotalAssets)
);

// 자산 값을 비율로 환산하여 그래프 높이 계산
const normalizedUserHeight = computed(() => {
  return (props.userTotalAssets / maxAssets.value) * maxGraphHeight;
});

const normalizedAverageHeight = computed(() => {
  return (props.averageTotalAssets / maxAssets.value) * maxGraphHeight;
});

// 데이터가 준비되었는지 확인하는 watch
watch(
  [() => props.userTotalAssets, () => props.averageTotalAssets],
  ([newUserTotalAssets, newAverageTotalAssets]) => {
    if (newUserTotalAssets && newAverageTotalAssets) {
      isTotalDataReady.value = true; // 데이터 준비 완료
      console.log("Data is ready for Total Assets Graph.");
    }
  }
);

// 컴포넌트가 마운트된 후 값이 존재하는지 체크
onMounted(() => {
  if (props.userTotalAssets && props.averageTotalAssets) {
    isTotalDataReady.value = true;
    console.log("Data is ready on mount for Total Assets Graph.");
  } else {
    console.error("Data is not ready for Total Assets Graph.");
  }
});
</script>
