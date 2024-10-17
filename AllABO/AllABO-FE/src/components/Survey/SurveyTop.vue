<template>
  <div
    class="bg-[#5F584E] h-[47%] flex flex-col items-center justify-center px-4 py-8 relative"
  >
    <ProgressBar
      :progress="progress"
      :currentStep="currentStep"
      :totalQuestion="totalQuestion"
      />

    <!-- 질문 텍스트 -->
    <h2
      class="text-white text-3xl font-bold text-center absolute w-full max-w-[760px]"
      style="top: 66.7%"
    >
      {{ currentQuestion.text }}
    </h2>

    <!-- 구분선 -->
    <div
      class="w-full max-w-[760px] h-px bg-white opacity-50 absolute left-1/2 transform -translate-x-1/2"
      style="bottom: 16.6%"
    ></div>
  </div>
</template>

<script setup>
import ProgressBar from './SurveyTop/ProgressBar.vue'

const props = defineProps({
    progress: Number,
    currentQuestion: Object,
    currentStep: Number,
    totalQuestion: Number,
})
</script>

<style scope>

</style>
