<template>
  <!-- 첫 번째 페이지 -->
  <div class="flex h-screen section bg-asset-back">
    <div class="flex items-center w-screen">
      <TotalCircle class="w-1/2 pl-40" />
      <TotalText class="w-1/2 pl-20" />
    </div>
  </div>

  <!--저축 분석 페이지 -->
  <div class="items-center h-screen section bg-asset-back">
    <AnalyzeSavings />
  </div>

  <!-- 자산, 부채 부석 페이지 -->
  <div class="items-center h-screen section bg-asset-back">
    <AnalyzeDebt />
  </div>

  <!--소득 분위별 분석-->
  <div class="items-center h-screen section bg-asset-back">
    <AnalyzeRange />
  </div>

  <div class="section h-[50%] items-center my-20">
    <AnalyzeButton />
  </div>
</template>

<script setup>
import TotalCircle from "../components/AssetAnalyze/TotalCircle.vue";
import TotalText from "../components/AssetAnalyze/TotalText.vue";
import AnalyzeDebt from "../components/AssetAnalyze/AnalyzeDebt.vue";
import AnalyzeSavings from "../components/AssetAnalyze/AnalyzeSavings.vue";
import AnalyzeRange from "../components/AssetAnalyze/AnalyzeRange.vue";
import AnalyzeButton from "../components/AssetAnalyze/AnalyzeButton.vue";

import { onBeforeMount, onMounted } from "vue";
import {
  getIncomeLevel,
  getLoan,
  getSaving,
  getSavingRatio,
  requestAnalyze,
} from "../apis/api/assetAnalyze";
import { loadingStateStore } from "../stores/loadingStateStore";

onBeforeMount(async ()=>{
  const res = await requestAnalyze();
  console.log(res);
})
</script>
