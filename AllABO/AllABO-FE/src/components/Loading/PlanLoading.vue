<template>
  <div class="absolute z-[49] w-full h-screen bg-white">
    <div class="absolute inset-0 flex items-center justify-center">
      <!-- 배경 노란 원 -->
      <div
        class="w-[700px] h-[700px] bg-kb-yellow-3 opacity-50 rounded-full blur-3xl -z-10"
      ></div>
    </div>

    <div class="absolute inset-0 z-50 flex items-center justify-center mr-52">
      <img
        src="/images/AssetPlanLoading/yellow-box.png"
        alt="yellow-box"
        class="w-[800px] h-auto mt-20 animate-float"
      />
      <div class="flex flex-col">
        <div class="mb-10 text-3xl text-font-color">
        <h1 class="pb-4">미래를 디자인하는 스마트한 선택</h1>
        <h1 class="pb-4">맞춤형 자산 관리에서 혁신적인 자산 설계까지</h1>
        <h1>당신만의 금융 여정이 시작됩니다.</h1>
      </div>

      <!-- 로딩 -->
      <div class="flex items-center">
      <div class="flex items-center gap-2">
        <div
          v-for="(item, index) in loadingDots"
          :key="index"
          class="w-1 h-1 rounded-full bg-kb-yellow-1 animate-bounce"
          :style="{ animationDelay: `${index * 0.2}s` }"
        ></div>
      </div>
      <span class="ml-2">로딩중</span>
    </div>
    </div>
  </div>
</div>
</template>

<script setup>
import { ref } from "vue";

const loadingDots = ref([0, 1, 2, 3]);
</script>

<style scoped>
@keyframes bounce {
  100% {
    transform: translateY(0); /* 처음과 끝 상태에서 원래 위치 */
  }
  50% {
    transform: translateY(-5px); /* 중간에서 위로 이동 */
  }
}

@keyframes float {
    0%, 100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-15px);
    }
}

.animate-bounce {
  animation: bounce 1s infinite; /* 1초마다 반복되는 애니메이션 */
}

.animate-float {
    animation: float 2s ease-in-out infinite;
}
</style>
