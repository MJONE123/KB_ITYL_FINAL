<template>
  <div class="flex flex-col items-center w-full">
    <p class="text-[32px] text-font-color font-semibold">저축 분석 결과</p>
    <div class="bg-gray-600 h-0.5 w-[185px]"></div>
  </div>

  <div
    ref="graphContainerRef"
    class="relative mx-[500px] flex flex-col justify-center items-center"
  >
    <!--bg 동그라미-->
    <div
      class="absolute top-16 left-0 right-0 bottom-0 rounded-full bg-kb-yellow-4 opacity-50 h-[600px] w-[600px] mx-auto shadow-[0_0_40px_20px_rgba(0,0,0,0.05)]"
    ></div>

    <!-- 연령대별 총 저축 금액 비교 -->
    <div class="relative z-10 flex flex-col items-center mt-32">
      <!-- 총 저축 금액 그래프 -->
      <div class="relative w-full max-w-[600px] h-[60px]">
        <!-- 평균 저축 금액 막대 -->
        <div
          class="absolute h-[60px] bg-kb-gray-2 rounded-r-lg origin-left transition-transform duration-1000 ease-out"
          :style="{
            width: `${(totalAverageSavings / maxTotalSavings) * maxBarWidth}px`,
            left: '50%',
            transform: isIntersecting ? 'scaleX(-1)' : 'scaleX(0)',
          }"
        ></div>
        <!-- 사용자 저축 금액 막대 -->
        <div
          class="absolute h-[60px] bg-kb-blue-4 rounded-r-lg origin-left transition-transform duration-1000 ease-out"
          :style="{
            width: `${(totalUserSavings / maxTotalSavings) * maxBarWidth}px`,
            left: '50%',
            transform: isIntersecting ? 'scaleX(1)' : 'scaleX(0)',
          }"
        ></div>
      </div>

      <!-- 저축 비교 설명 -->
      <div class="mb-20 space-y-2">
        <p class="text-[20px] text-kb-brown-6 mt-4">
          {{ age }}대 평균 저축액보다
        </p>
        <p class="font-bold text-[36px] text-font-color">
          약 {{ Math.abs(parseFloat(compareSavings1)).toFixed(1) }}배 더
          {{ parseFloat(compareSavings1) >= 0 ? '높습니다' : '낮습니다' }}
        </p>
        <p class="text-kb-blue-5 font-semibold text-[26px]">
          {{ totalSavingsKeywords.join(', ') }}
        </p>
      </div>
    </div>

    <!-- 연령대별 평균 자산 대비 저축 비율 비교 -->
    <div class="relative z-10 flex flex-col items-center">
      <!-- 자산 대비 저축 비율 그래프 -->
      <div class="rotate-180 relative w-full max-w-[600px] h-[60px]">
        <!-- 평균 자산 대비 저축 비율 막대 -->
        <div
          class="absolute h-[60px] bg-kb-gray-2 rounded-r-lg origin-left transition-transform duration-1000 ease-out"
          :style="{
            width: `${(assetAverageSavings / maxAssetSavings) * maxBarWidth}px`,
            left: '50%',
            transform: isIntersecting ? 'scaleX(-1)' : 'scaleX(0)',
          }"
        ></div>
        <!-- 사용자 자산 대비 저축 비율 막대 -->
        <div
          class="absolute h-[60px] bg-kb-yellow-3 rounded-r-lg origin-left transition-transform duration-1000 ease-out"
          :style="{
            width: `${(assetUserSavings / maxAssetSavings) * maxBarWidth}px`,
            left: '50%',
            transform: isIntersecting ? 'scaleX(1)' : 'scaleX(0)',
          }"
        ></div>
      </div>

      <!-- 자산 대비 저축 비교 설명 -->
      <div class="mb-10 space-y-2">
        <p class="text-[20px] text-kb-brown-6 mt-4">
          {{ age }}대 평균 자산 대비 저축 비율보다
        </p>
        <p class="font-bold text-[36px] text-font-color">
          약 {{ Math.abs(parseFloat(compareSavings2)).toFixed(1) }}배 더
          {{ parseFloat(compareSavings2) >= 0 ? '높습니다' : '낮습니다' }}
        </p>
        <p class="text-kb-blue-5 font-semibold text-[26px]">
          {{ savingsRatioKeywords.join(', ') }}
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

import { useAuthStore } from '../../stores/auth';
import { loadingStateStore } from '../../stores/loadingStateStore';

// IntersectionObserver 상태 관리
const isIntersecting = ref(false);
const graphContainerRef = ref(null);

// 비교군
const age = ref(20);

// 평균 저축액 비교
const totalUserSavings = ref(0);
const totalAverageSavings = ref(0);

// 평균 자산 대비 저축 비율
const assetUserSavings = ref(0);
const assetAverageSavings = ref(0);

const compareSavings1 = ref('0');
const compareSavings2 = ref('0');
const totalSavingsKeywords = ref([]);
const savingsRatioKeywords = ref([]);

const loadingStore = loadingStateStore();

// 최대 막대 너비 및 최대값 계산
const maxBarWidth = 400;
const maxTotalSavings = ref(0);
const maxAssetSavings = ref(0);

// 데이터 가져오기 및 처리
const fetchSavingsAnalysisData = async () => {
  const authStore = useAuthStore();
  const token = authStore.token;
  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }

  try {
    const response = await axios.post(
      'http://localhost:8080/assets/saving-ratio',
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }
    );

    const data = response.data.jsonNode;

    // 값 업데이트
    age.value = parseInt(data.비교균);

    totalUserSavings.value = parseInt(
      data['총 저축 금액 분석']['내 자산'].replace('원', '')
    );
    totalAverageSavings.value = parseInt(
      data['총 저축 금액 분석']['비교군 자산'].replace('원', '')
    );
    compareSavings1.value = data['총 저축 금액 분석']['비교군과 비교%'].replace(
      '%',
      ''
    );
    totalSavingsKeywords.value = data['총 저축 금액 분석']['요약'];

    assetUserSavings.value = parseFloat(
      data['자산 대비 저축 비율 분석']['자산 대비 저축 비율'].replace('%', '')
    );
    assetAverageSavings.value = parseFloat(
      data['자산 대비 저축 비율 분석']['비교균 저축 비율'].replace('%', '')
    );
    compareSavings2.value = data['자산 대비 저축 비율 분석']['차이'].replace(
      '%',
      ''
    );
    savingsRatioKeywords.value = data['자산 대비 저축 비율 분석']['요약'];

    // 최대값 계산
    maxTotalSavings.value = Math.max(
      totalUserSavings.value,
      totalAverageSavings.value
    );
    maxAssetSavings.value = Math.max(
      assetUserSavings.value,
      assetAverageSavings.value
    );
  } catch (error) {
    console.error('데이터 가져오기 오류', error);
  }
};

// IntersectionObserver 설정
const observeScroll = () => {
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          isIntersecting.value = true;
          observer.disconnect(); // 애니메이션 실행 후 해제
        }
      });
    },
    { threshold: 0.5 } // 50% 이상 보일 때 실행
  );

  if (graphContainerRef.value) {
    observer.observe(graphContainerRef.value);
  }
};

onMounted(() => {
  fetchSavingsAnalysisData();
  observeScroll();
});
</script>

<style scoped>
/* 필요에 따라 스타일 추가 */
</style>
