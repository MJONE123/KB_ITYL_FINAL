<template>
    <div
      class="w-[700px] h-[800px] border-8 border-kb-yellow-1 mt-20 bg-white flex flex-col items-center"
    >
      <div
        class="bg-kb-brown-2 h-[40px] w-[150px] mt-12 mb-7 text-white rounded-full flex items-center justify-center"
      >
        내 보험 분석
      </div>
      <h1
        v-for="(item, index) in insurance"
        :key="index"
        class="text-4xl font-bold text-font-color p-3"
      >
        {{ item }}
      </h1>
      <div class="flex flex-col items-center my-8">
        <div
          v-for="i in 3"
          :key="i"
          class="w-1 h-1 bg-font-color rounded-full m-2"
        ></div>
      </div>
      <div class="flex flex-col items-center w-[450px]">
        <p class="text-xl text-font-color">{{ desc }}</p>
      </div>
    </div>
</template>

<script setup>
const props = defineProps({
    insurance: {
        type: Array,
        required: true,
    },
    desc: {
        type: String,
        required: true,
    }
})
</script>

<style>
    
</style>