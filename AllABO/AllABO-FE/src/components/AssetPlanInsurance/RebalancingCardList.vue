<template>
  <div class="flex justify-cente space-x-10">
    <RebalancingCard
      v-for="(title, index) in rebalancingTitle"
      :key="index"
      :title="title"
      :content="rebalancingContent[title]['설명']"
      :isMaintain="insuranceContent[insuranceName[index]]['유지_권장']"
    />
  </div>
</template>

<script setup>
import RebalancingCard from '../AssetPlanInsurance/RebalancingCardList/RebalancingCard.vue';

const props = defineProps({
  rebalancingTitle: {
    type: Array,
    required: true,
  },
  rebalancingContent: {
    type: Object,
    required: true,
  },
  insuranceName: {
    type: Array,
    required: true,
  },
  insuranceContent: {
    type: Object,
    required: true,
  },
});
</script>
