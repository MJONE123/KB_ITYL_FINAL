<template>
  <div class="flex justify-cente space-x-10">
    <InsuranceCard
      v-for="(name, index) in insuranceName"
      :key="index"
      :name="name"
      :content="insuranceContent[name]"
    />
  </div>
</template>

<script setup>
import InsuranceCard from './InsuranceCardList/InsuranceCard.vue';

const props = defineProps({
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
