<template>
  <div class="h-[70px] mb-12"></div>
  <!-- <div class="flex flex-col items-center "> -->
  <h1 class="p-2 text-4xl font-bold text-center text-font-color">보험 리밸런싱</h1>
  <p class="text-sm text-center text-kb-gray-1">제공된 가입 보험 내역을 바탕으로 한 리밸런싱 결과로, 실제 데이터와 차이가 있을 수 있습니다.</p>
  <!-- 
      <InsuranceAnalyze :insurance="insurance" :desc="desc" /> -->
  <!-- </div> -->

  <div class="mt-8">
    <div class="flex flex-col items-center w-full bg-cover bg-rebalancing-back">
      <AssetPlanTabButton v-model="showCurrentAnalysis" />

      <div class="w-full p-10">
        <InsuranceCardList class="flex justify-center" v-if="showCurrentAnalysis" :insuranceName="insuranceName" :insuranceContent="insuranceContent" />
        <RebalancingCardList
          class="flex justify-center"
          v-else
          :rebalancingTitle="rebalancingTitle"
          :rebalancingContent="rebalancingContent"
          :insuranceName="insuranceName"
          :insuranceContent="insuranceContent"
        />
      </div>
      <div class="h-[200px] text-center">
        <CommonButton @click="goToAssetPlan" class="mt-10" :text="'돌아가기'" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import InsuranceAnalyze from "../components/AssetPlanInsurance/InsuranceAnalyze.vue";
import InsuranceCardList from "../components/AssetPlanInsurance/InsuranceCardList.vue";
import RebalancingCardList from "../components/AssetPlanInsurance/RebalancingCardList.vue";
import AssetPlanTabButton from "../components/AssetPlanInsurance/AssetPlanTabButton.vue";
import CommonButton from "../components/common/CommonButton.vue";

import axios from "axios";
import { useAuthStore } from "../stores/auth";
import { loadingStateStore } from "../stores/loadingStateStore";

const router = useRouter();
const showCurrentAnalysis = ref(true);

// 보험 리밸런싱 페이지 데이터
const insuranceName = ref([]);
const insuranceContent = ref({});
const rebalancingTitle = ref([]);
const rebalancingContent = ref({});

const loadingStore = loadingStateStore();

// axios 연결
const fetchInsuranceRebalancing = async () => {
  const authStore = useAuthStore();
  const token = authStore.token;

  if (!token) {
    console.error("토큰이 없습니다. 로그인 후 다시 시도하세요.");
    return;
  }

  try {
    loadingStore.setIsAssetPlanLoadingState(true);
    const response = await axios.post(
      `http://localhost:8080/insurance/rebalancing`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      }
    );
    if (response.status == 200) {
      loadingStore.setIsAssetPlanLoadingState(false);
    }
    const data = response?.data?.rebalancingProposal?.리밸런싱_제안;

    insuranceName.value = Object.keys(data.현재_보험_리스트);
    insuranceContent.value = data.현재_보험_리스트;
    rebalancingTitle.value = Object.keys(data.제안);
    rebalancingContent.value = data.제안;
  } catch (error) {
    console.error("데이터 가져오기 실패: ", error);
  }
};

const goToAssetPlan = () => {
  router.push("/asset-plan");
};

onMounted(() => {
  fetchInsuranceRebalancing();
});
</script>

<style></style>
