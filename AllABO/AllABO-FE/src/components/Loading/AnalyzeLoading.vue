<template>
  <div
    class="overflow-hidden absolute flex items-center w-screen h-screen bg-white z-[49]"
  >
    <div class="flex-shrink-0 w-[580px] flex flex-col items-center">
      <div>
        <h3 class="mb-2 text-[28px]">
          <span class="font-bold text-kb-yellow-1 text-[32px]">KB 국민은행</span
          >에서 <br />데이터를 가져와 <br />고객님의 분석 중입니다.
        </h3>

        <!-- 로딩 -->
        <div class="flex">
          <div class="flex items-center gap-2">
            <div
              v-for="(item, index) in loadingDots"
              :key="index"
              class="w-1 h-1 rounded-full bg-kb-yellow-1 animate-bounce"
              :style="{ animationDelay: `${index * 0.2}s` }"
            ></div>
            로딩중
          </div>
        </div>
      </div>
    </div>

    <!-- 캐러셀 -->
    <div class="relative overflow-hidden w-[100vw] h-[480px] flex items-center">
      <div
        class="flex"
        :style="{
          transform: `translateX(-${
            currentIndex * (isLaptopView ? 350 : 400)
          }px)`,
          transition: isTransitionActive ? 'transform 0.7s ease' : 'none',
        }"
      >
        <div
          v-for="(item, index) in imageIndex"
          :key="index"
          :style="{
            backgroundImage: `url('/images/AnalyzeLoading/carousel${
              item + 1
            }.png')`,
            transition: 'transform 0.7s ease;',
          }"
          class="md:max-laptop:w-[350px] md:max-laptop:h-[350px] w-[400px] h-[400px] bg-cover bg-no-repeat shrink-0"
        ></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount } from "vue";

const loadingDots = ref([0, 1, 2, 3]);

const imageIndex = ref([0, 1, 2, 3, 0, 1, 2]); // 마지막 이미지는 첫 이미지와 동일
const currentIndex = ref(0); // 현재 보여줄 이미지의 인덱스
const isTransitionActive = ref(true); // 애니메이션 활성화 여부

setInterval(() => {
  if (currentIndex.value === imageIndex.value.length - 4) {
    // 마지막 인덱스일 경우 애니메이션 끄고 첫 번째로 이동
    isTransitionActive.value = true;
    currentIndex.value += 1;

    setTimeout(() => {
      isTransitionActive.value = false;
      currentIndex.value = 0;
    }, 700); // 애니메이션 완료 후 0으로 이동
  } else {
    // 일반적인 슬라이딩
    isTransitionActive.value = true;
    currentIndex.value += 1;
  }
}, 2000);

// 창의 너비를 저장
const windowWidth = ref(window.innerWidth);

// 화면 크기에 따라 반응형을 위한 상태 설정
const isLaptopView = computed(() => windowWidth.value <= 1537);

// 창 크기 변경 이벤트 리스너
const updateWindowWidth = () => {
  windowWidth.value = window.innerWidth;
};

onMounted(() => {
  window.addEventListener("resize", updateWindowWidth);
});

onBeforeUnmount(() => {
  window.removeEventListener("resize", updateWindowWidth);
});
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

.animate-bounce {
  animation: bounce 1s infinite; /* 1초마다 반복되는 애니메이션 */
}
</style>
