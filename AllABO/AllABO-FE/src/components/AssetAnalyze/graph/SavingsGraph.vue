<template>
  <div
    id="graphContainer"
    ref="graphContainerRef"
    class="relative w-full flex justify-center space-y-4"
  >
    <!-- 첫 번째 그래프 -->
    <div
      class="relative w-full max-w-[600px] h-[60px] ml-52"
      v-if="isSavingDataReady"
    >
      <div
        class="absolute h-[60px] bg-kb-gray-2 rounded-r-lg origin-left"
        :style="{
          width: `${animatedAverageWidth}px`,
          left: '50%',
          transform: 'scaleX(-1)',
        }"
      ></div>
      <div
        class="absolute h-[60px] rounded-r-lg origin-left"
        :style="{ width: `${animatedUserWidth}px`, left: '50%' }"
        :class="userClass1"
      ></div>
    </div>
    <div v-else class="text-center">그래프 로딩 중...</div>

    <!-- 두 번째 그래프 -->
    <div
      class="relative w-full max-w-[600px] h-[60px] mt-8 mr-24"
      v-if="isSavingDataReady"
    >
      <div
        class="absolute h-[60px] bg-kb-gray-2 rounded-r-lg origin-left"
        :style="{
          width: `${animatedAverageWidth2}px`,
          left: '50%',
          transform: 'scaleX(-1)',
        }"
      ></div>
      <div
        class="absolute h-[60px] rounded-r-lg origin-left"
        :style="{ width: `${animatedUserWidth2}px`, left: '50%' }"
        :class="userClass2"
      ></div>
    </div>
    <div v-else class="text-center">그래프 로딩 중...</div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, watch } from "vue";

// Props로 받은 데이터
const props = defineProps({
  totalUserSavings: Number,
  totalAverageSavings: Number,
  assetUserSavings: Number,
  assetAverageSavings: Number, // 데이터 통합
});

console.log(
  props.totalUserSavings,
  props.totalAverageSavings,
  props.assetUserSavings,
  props.assetAverageSavings
);

// 요소 참조와 데이터 준비 상태 관리
const graphContainerRef = ref(null);
const isSavingDataReady = ref(false);

// 최대 막대 너비 설정
const maxBarWidth = 300;
const animatedUserWidth = ref(0);
const animatedAverageWidth = ref(0);
const animatedUserWidth2 = ref(0);
const animatedAverageWidth2 = ref(0);

// 막대 색상 클래스
const userClass1 = "bg-kb-blue-4";
const userClass2 = "bg-kb-yellow-3";

// 첫 번째 그래프 계산
const calculateWidths1 = (userValue, averageValue) => {
  const maxAssets = Math.max(userValue, averageValue);
  animatedUserWidth.value = (userValue / maxAssets) * maxBarWidth;
  animatedAverageWidth.value = (averageValue / maxAssets) * maxBarWidth;
  console.log(
    "첫 번째 그래프 값: ",
    animatedUserWidth.value,
    animatedAverageWidth.value
  );
};

// 두 번째 그래프 계산
const calculateWidths2 = (userValue, averageValue) => {
  const maxAssets = Math.max(userValue, averageValue);
  animatedUserWidth2.value = (userValue / maxAssets) * maxBarWidth;
  animatedAverageWidth2.value = (averageValue / maxAssets) * maxBarWidth;
  console.log(
    "두 번째 그래프 값: ",
    animatedUserWidth2.value,
    animatedAverageWidth2.value
  );
};

// IntersectionObserver를 이용한 스크롤 감지
const observeScroll = (userValue, averageValue, isFirstGraph = true) => {
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          if (isFirstGraph) {
            console.log(userValue, averageValue);
            calculateWidths1(userValue, averageValue);
          } else {
            calculateWidths2(userValue, averageValue);
          }
          isSavingDataReady.value = true; // 데이터가 준비됨을 표시
        }
      });
    },
    { threshold: 0.5 } // 50% 이상 보일 때 실행
  );

  if (graphContainerRef.value) {
    observer.observe(graphContainerRef.value);
  }
};

// 데이터가 준비되었는지 확인하고 observer 실행
watch(
  [
    props.totalUserSavings,
    props.totalAverageSavings,
    props.assetUserSavings,
    props.assetAverageSavings,
  ],
  ([
    newUserTotalSavings,
    newAverageTotalSavings,
    newAssetUserSavings,
    newAssetAverageSavings,
  ]) => {
    if (
      newUserTotalSavings !== undefined &&
      newAverageTotalSavings !== undefined &&
      newAssetUserSavings !== undefined &&
      newAssetAverageSavings !== undefined
    ) {
      nextTick(() => {
        observeScroll(newUserTotalSavings, newAverageTotalSavings, true); // 첫 번째 그래프
        observeScroll(newAssetUserSavings, newAssetAverageSavings, false); // 두 번째 그래프
      });
    }
  }
);

// 컴포넌트 마운트 시 IntersectionObserver 등록
onMounted(() => {
  nextTick(() => {
    if (
      props.totalUserSavings !== undefined &&
      props.totalAverageSavings !== undefined &&
      props.assetUserSavings !== undefined &&
      props.assetAverageSavings !== undefined
    ) {
      isSavingDataReady.value = true;
      observeScroll(props.totalUserSavings, props.totalAverageSavings, true); // 첫 번째 그래프
      observeScroll(props.assetUserSavings, props.assetAverageSavings, false); // 두 번째 그래프
    }
  });
});
</script>
