<template>
  <div class="h-screen flex flex-col overflow-hidden">
    <!-- 소개 -->
    <div class="h-[40%] flex px-[17%] py-[2%] items-end text-font-color">
      <h1>
        <span>{{ userName }}님의 자산과 보험 여부를 고려한</span><br />
        <span class="font-bold text-[42px]">미래 자산 설계</span>
      </h1>
    </div>

    <!-- 네비게이션 -->
    <div
      class="h-[70%] bg-kb-yellow-4 flex items-center justify-center gap-5 px-[12%]"
    >
      <div
        v-for="(item, index) in navItem"
        :key="index"
        class="flex flex-col justify-end flex-1 pl-4 h-[50%] pr-16 cursor-pointer pb-7 bg-kb-brown-2 hover:bg-[#817058] duration-300"
        @click="router.push(item.url)"
      >
        <p class="text-white text-[24px] pl-2">
          <span class="text-[60px] pr-2">{{ item.id }}</span> {{ item.text }}
        </p>
        <div class="w-full bg-white h-[1px]"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '../../stores/auth';

const router = useRouter();
const authStore = useAuthStore();

const userName = authStore.$state.name;

const navItem = [
  { id: '01', text: '맞춤 개선 전략', url: '/asset-plan/strategy' },
  { id: '02', text: '1년 후 예상 자산', url: '/asset-plan/simulation' },
  { id: '03', text: '보험 리밸런싱', url: 'asset-plan/insurance' },
];
</script>
