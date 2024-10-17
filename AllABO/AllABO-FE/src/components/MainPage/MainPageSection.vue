<template>
  <div :class="['flex items-center justify-center h-screen py-8 px-[100px] gap-[100px]', item.left ? 'bg-kb-brown-2' : 'bg-white']">
    <div :class="['flex items-center justify-center h-full']">
      <div class="bg-[url('/images/MainPage/macbook.png')] w-[1000px] h-[600px] md:max-laptop:w-[600px] md:max-laptop:h-[400px] bg-contain bg-no-repeat"></div>
    </div>

    <div :class="['flex flex-col justify-center gap-8', item.left ? '' : 'order-first']">
      <div>
        <div class="relative inline">
          <h1 :class="['md:max-laptop:text-[22px] text-[28px] inline', item.left ? 'text-white' : '']">{{ item.title }}</h1>
          <div class="absolute bottom-0 left-4 bg-kb-yellow-1 w-[93%] h-[50%] opacity-50 inline"></div>
        </div>
      </div>

      <h3 :class="['md:max-laptop:text-[28px] text-[36px] font-bold', item.left ? 'text-white' : '']" >{{ item.mainContent }}</h3>
      <h3 :class="['md:max-laptop:text-[18px] text-[24px] whitespace-pre-wrap', item.left ? 'text-white' : '']">{{ item.subContent }}</h3>
      <div>
        <MainPageButton :text="item.buttonText" :left="item.left" />
      </div>
    </div>
  </div>
</template>

<script setup>
import MainPageButton from "./MainPageButton.vue";

const props = defineProps({
  item: { type: Object, required: true },
});

// console.log(props)
</script>
