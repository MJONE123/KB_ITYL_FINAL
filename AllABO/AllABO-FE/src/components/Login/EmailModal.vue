<template>
  <div class="fixed inset-0 bg-gray-600 bg-opacity-75 flex justify-center items-center z-50">
    <div class="bg-white p-8 rounded-lg shadow-lg w-[650px] relative">
      <!-- Close Button -->
      <button @click="$emit('close')" class="absolute top-4 right-4 text-2xl font-bold text-gray-400 hover:text-gray-600 transition duration-150">✕</button>
      
      <!-- Logo and Title -->
      <div class="text-center mb-6">
        <p class="text-3xl font-bold text-gray-700">LOGO</p>
        <p class="text-xl font-semibold text-gray-700 mt-4">이메일 찾기</p>
      </div>
      
      <!-- Form -->
      <form @submit.prevent="handleSubmit">
        <!-- 이름 입력 -->
        <div class="mb-6 px-6">
          <label for="name" class="block text-left text-gray-600 mb-2">이름</label>
          <input type="text" id="name" placeholder="이름을 입력해주세요" 
                 class="pl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-150"/>
        </div>
        
        <!-- 전화번호 입력 -->
        <div class="mb-8 px-6">
          <label for="tel" class="block text-left text-gray-600 mb-2">전화번호</label>
          <input type="text" id="tel" v-model="tel" placeholder="전화번호를 입력해주세요" 
                 class="pl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-150"/>
        </div>
        
        <!-- 제출 버튼 -->
        <div class="px-6">
          <button type="submit" class="h-[50px] w-full rounded-md text-white bg-kb-brown-2 hover:bg-kb-yellow-1 focus:bg-kb-yellow-1 transition-colors duration-150">
            이메일 찾기
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
const handleSubmit = () => {
  alert('이메일 찾기 요청을 보냈습니다.');
};
</script>
