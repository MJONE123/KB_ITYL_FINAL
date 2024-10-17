<template>
  <div class="h-[100vh] flex flex-col">
    <div class="h-[70px] bg-kb-yellow-4"></div>

    <div
      class="relative flex items-center justify-center h-full overflow-hidden bg-kb-yellow-4"
    >
      <div
        class="relative z-10 flex items-center w-full px-[18%] md:max-laptop:px-[16.5%]"
      >
        <h1
          class="float-left font-bold bg-kb-yellow-4 text-[32px] md:max-laptop:text-[24px] whitespace-nowrap"
        >
          클릭 한 번으로 완성되는
        </h1>
        <p
          class="float-left ml-[11%] text-center bg-kb-yellow-4 whitespace-nowrap"
        >
          현금, 카드, 보험, 대출<br />모든 자산을 한 번에 분석하는 F:YL 만의
          압도적인 서비스
        </p>
        <h1
          class="float-left ml-44 font-bold bg-kb-yellow-4 text-[32px] md:max-laptop:text-[24px] whitespace-nowrap"
        >
          자산 분석
        </h1>
      </div>

      <div class="absolute top-[75%] z-40">
        <MainPageButton :text="'분석하러 가기'" @click="goToAnalyze" />
      </div>

      <!-- 첫번째 원 -->
      <div class="absolute">
        <div
          class="border border-kb-gray-3 w-[350px] h-[350px] rounded-full relative"
        >
          <!-- <span class="absolute size-[38px] rounded-full bg-kb-yellow-1 dot opacity-70"></span> -->
          <span
            class="absolute rounded-full opacity-70 size-5 bg-kb-yellow-2 dot"
          ></span>
          <span
            class="absolute size-[38px] rounded-full bg-kb-brown-4 dot opacity-70"
          ></span>
        </div>
      </div>

      <!-- 두번째 원 -->
      <div class="absolute">
        <div class="border border-kb-gray-3 w-[650px] h-[650px] rounded-full">
          <!-- <span class="absolute size-[52px] rounded-full bg-kb-yellow-1 dot dot2"></span> -->
          <span
            class="absolute size-[42px] rounded-full opacity-60 bg-kb-yellow-2 dot dot2"
          ></span>
          <span
            class="absolute size-[62px] rounded-full opacity-60 bg-kb-pink-1 dot dot2"
          ></span>
        </div>
      </div>

      <!-- 세번째 원 -->
      <div class="absolute">
        <div class="border border-kb-gray-3 w-[1100px] h-[1100px] rounded-full">
          <!-- <span class="absolute size-[68px] rounded-full opacity-50 bg-kb-gray-2 dot dot3"></span> -->
          <span
            class="absolute size-[100px] rounded-full opacity-50 bg-kb-gray-1 dot dot3"
          ></span>
          <span
            class="absolute size-[80px] rounded-full opacity-50 bg-kb-pink-1 dot dot3"
          ></span>
        </div>
      </div>

      <!-- 네번째 원 -->
      <div class="absolute">
        <div class="border border-kb-gray-3 w-[1500px] h-[1500px] rounded-full">
          <span
            class="absolute size-[120px] rounded-full opacity-20 bg-kb-gray-2 dot dot4"
          ></span>
          <!-- <span class="absolute size-[140px] rounded-full opacity-20 bg-kb-gray-1 dot dot4"></span> -->
          <span
            class="absolute size-[200px] rounded-full opacity-20 bg-kb-pink-1 dot dot4"
          ></span>
        </div>
      </div>

      <div class="w-[80%] h-[0.5px] bg-black absolute"></div>
    </div>
  </div>
</template>
<script setup>
import MainPageButton from '../components/MainPage/MainPageButton.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const goToAnalyze = () => {
  router.push('/asset-analyze');
};
</script>

<style scoped>
.dot {
  position: absolute;
  top: 50%;
  left: 50%;
  z-index: 20;
}

/* 각 dot의 초기 위치 설정 */
.dot:nth-child(1) {
  animation: orbitLeft 10s linear infinite; /* 원을 도는 애니메이션 */
}

.dot:nth-child(2) {
  animation: orbitRight 10s linear infinite; /* 원을 도는 애니메이션 */
}

.dot:nth-child(3) {
  animation: orbitLeft 7s linear infinite; /* 원을 도는 애니메이션 */
}

/* 2번째 원 */
/* 각 dot의 초기 위치 설정 */
.dot2:nth-child(1) {
  animation: orbitLeft2 10s linear infinite; /* 원을 도는 애니메이션 */
}

.dot2:nth-child(2) {
  animation: orbitRight2 10s linear infinite; /* 원을 도는 애니메이션 */
}

.dot2:nth-child(3) {
  animation: orbitLeft2 7s linear infinite; /* 원을 도는 애니메이션 */
}

/* 3번째 원 */
/* 각 dot의 초기 위치 설정 */
.dot3:nth-child(1) {
  animation: orbitLeft3 13s linear infinite; /* 원을 도는 애니메이션 */
}

.dot3:nth-child(2) {
  animation: orbitRight3 9s linear infinite; /* 원을 도는 애니메이션 */
}

.dot3:nth-child(3) {
  animation: orbitLeft3 8s linear infinite; /* 원을 도는 애니메이션 */
}

/* 4번째 원 */
/* 각 dot의 초기 위치 설정 */
.dot4:nth-child(1) {
  animation: orbitLeft4 13s linear infinite; /* 원을 도는 애니메이션 */
}

.dot4:nth-child(2) {
  animation: orbitRight4 9s linear infinite; /* 원을 도는 애니메이션 */
}

.dot4:nth-child(3) {
  animation: orbitLeft4 8s linear infinite; /* 원을 도는 애니메이션 */
}

/* 원을 도는 애니메이션 정의 */
@keyframes orbitLeft {
  from {
    transform: translate(-50%, -50%) rotate(0deg) translateX(175px);
  }
  to {
    transform: translate(-50%, -50%) rotate(360deg) translateX(175px);
  }
}
@keyframes orbitRight {
  from {
    transform: translate(-50%, -50%) rotate(360deg) translateX(175px);
  }
  to {
    transform: translate(-50%, -50%) rotate(0deg) translateX(175px);
  }
}
/* 2번째 영역 */
/* 원을 도는 애니메이션 정의 */
@keyframes orbitLeft2 {
  from {
    transform: translate(-50%, -50%) rotate(180deg) translateX(325px);
  }
  to {
    transform: translate(-50%, -50%) rotate(540deg) translateX(325px);
  }
}
@keyframes orbitRight2 {
  from {
    transform: translate(-50%, -50%) rotate(540deg) translateX(325px);
  }
  to {
    transform: translate(-50%, -50%) rotate(180deg) translateX(325px);
  }
}

/* 3번째 영역 */
/* 원을 도는 애니메이션 정의 */
@keyframes orbitLeft3 {
  from {
    transform: translate(-50%, -50%) rotate(0deg) translateX(550px);
  }
  to {
    transform: translate(-50%, -50%) rotate(360deg) translateX(550px);
  }
}
@keyframes orbitRight3 {
  from {
    transform: translate(-50%, -50%) rotate(360deg) translateX(550px);
  }
  to {
    transform: translate(-50%, -50%) rotate(0deg) translateX(550px);
  }
}

/* 3번째 영역 */
/* 원을 도는 애니메이션 정의 */
@keyframes orbitLeft4 {
  from {
    transform: translate(-50%, -50%) rotate(540deg) translateX(750px);
  }
  to {
    transform: translate(-50%, -50%) rotate(180deg) translateX(750px);
  }
}
@keyframes orbitRight4 {
  from {
    transform: translate(-50%, -50%) rotate(180deg) translateX(750px);
  }
  to {
    transform: translate(-50%, -50%) rotate(540deg) translateX(750px);
  }
}
</style>
