<template>
    <div class="flex items-center justify-center h-screen py-8 px-[100px] gap-[100px] bg-kb-yellow-4">
     <!--이것은 노란색 원-->
<div class="w-full h-[600px] rounded-full bg-kb-yellow-12">

<div class="flex">
    <div class="flex w-full h-[300px] rounded-full bg-kb-yellow-13 mt-40 mr-12">

    </div>
     <!-- 왼쪽에 텍스트와 버튼 배치 -->
     <div class="absolute text-center">
        <h1 class="text-[28px] font-bold">{{ item.title }}</h1>
        <div class="absolute bottom-0 left-4 bg-kb-yellow-1 w-[93%] h-[50%] opacity-50 inline"></div>

        <h3 class="text-[36px] font-bold mt-4">{{ item.mainContent }}</h3>
        <h3 class="text-[24px] mt-2">{{ item.subContent }}</h3>
        <div class="mt-8">
          <MainPageButton :text="item.buttonText" />
        </div>
      </div>
</div>
     </div>
  
      <!-- 오른쪽에 모니터 이미지 배치 -->
      <div class="flex items-center justify-center h-full">
        <div class="bg-[url('/images/MainPage/macbook.png')] w-[1000px] h-[300px] md:max-laptop:w-[600px] md:max-laptop:h-[400px] bg-contain bg-no-repeat"></div>
      </div>
    </div>
  </template>
  
  <script setup>
  import MainPageButton from "./MainPageButton.vue";
  
  const props = defineProps({
    item: { type: Object},
  });
  </script>
  