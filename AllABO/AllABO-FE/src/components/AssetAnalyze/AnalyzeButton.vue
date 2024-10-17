<template>
  <div class="flex flex-col items-center">
    <div class="mb-6 text-[16px] text-kb-gray-1">
      고객님의 자산 관리 유형에 따른
    </div>
    <div>
      <button
        @click="goToAssetPlan"
        class="text-lg w-[180px] h-[50px] px-6 py-3 text-font-color font-semibold bg-kb-yellow-10 rounded-[10px] shadow-md hover:bg-kb-yellow-12 transition duration-300 ease-in-out"
      >
        자산설계 하러가기
      </button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToAssetPlan = () => {
  router.push('/asset-plan');
};
</script>
