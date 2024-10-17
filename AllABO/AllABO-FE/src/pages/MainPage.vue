<template>
  <div>
    <!-- 처음 -->
    <MainPageFirst />
    <!-- 두번쨰 -->
    <div class="flex items-center justify-center h-screen bg-kb-yellow-4">
      <div
        class="bg-kb-yellow-1 blur-[80px] md:max-laptop:w-[320px] md:max-laptop:h-[320px] w-[460px] h-[460px] rounded-full"
      ></div>
      <p
        class="absolute md:max-laptop:text-[24px] text-[36px] text-center text-font-color"
      >
        혁신적인 금융자산 분석 비교 서비스<br />
        기존에 없던 편리한 금융 서비스를 경험하세요.
      </p>
    </div>
    <MainPageNavigation />
  </div>
</template>

<script setup>
import MainPageFirst from '../components/MainPage/MainPageFirst.vue';
import MainPageNavigation from '../components/MainPage/MainPageNavigation.vue';
</script>
