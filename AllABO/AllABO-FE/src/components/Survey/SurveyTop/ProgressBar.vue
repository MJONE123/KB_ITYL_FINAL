<template>
  <div
    class="absolute w-full max-w-[528px] left-1/2 transform -translate-x-1/2"
    style="top: 50%"
  >
    <div class="absolute top-[-24px] left-0 text-white text-sm">
      {{ currentStep }} / {{ totalQuestion }}
    </div>
    <div class="progress-bar bg-gray-300 h-2 rounded-full overflow-hidden">
      <div
        class="bg-yellow-400 h-full duration-200 ease-out"
        :style="{ width: `${progress}%` }"
      ></div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  progress: Number,
  currentStep: Number,
  totalQuestion: Number,
});
</script>

<style scope>
.progress-bar {
  border-radius: 40px;
}
</style>
