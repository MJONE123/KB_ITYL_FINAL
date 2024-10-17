<template>
  <div
    class="fixed inset-0 bg-gray-600 bg-opacity-75 flex justify-center items-center z-50"
  >
    <div class="bg-white p-6 rounded-lg shadow-lg w-[650px] relative">
      <button
        @click="$emit('close')"
        class="absolute top-4 right-4 text-2xl font-bold text-gray-400 hover:text-gray-600"
      >
        ✕
      </button>

      <!-- 제목 -->
      <div class="text-center mb-6">
        <p class="text-3xl font-bold text-gray-700">비밀번호 찾기</p>
        <p class="text-gray-500 mt-2">회원님의 정보를 입력해 주세요</p>
      </div>

      <!-- 폼 -->
      <form @submit.prevent="handleSubmit">
        <!-- 이름 입력 -->
        <div class="mb-5">
          <label for="name" class="block text-left text-gray-600 mb-1"
            >이름</label
          >
          <input
            type="text"
            id="name"
            v-model="name"
            placeholder="이름을 입력해주세요"
            class="pl-4 h-[45px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-1"
          />
        </div>

        <!-- 전화번호 입력 -->
        <div class="mb-5">
          <label for="tel" class="block text-left text-gray-600 mb-1"
            >전화번호</label
          >
          <input
            type="text"
            id="tel"
            v-model="tel"
            placeholder="전화번호를 입력해주세요 (01012341234)"
            class="pl-4 h-[45px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-1"
          />
          <!-- 전화번호 유효성 검사 경고 메시지 -->
          <p v-if="telError" class="text-kb-gray-2 text-sm mt-1 ml-1">
            전화번호는 숫자만 10~11자로 입력해주세요.
          </p>
        </div>

        <!-- 생년월일 입력 -->
        <div class="mb-8">
          <label for="birthday" class="block text-left text-gray-600 mb-1"
            >생년월일</label
          >
          <input
            type="text"
            id="birthday"
            v-model="birthday"
            placeholder="YYYYMMDD 형식으로 입력해주세요"
            class="pl-4 h-[45px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-1"
          />
          <!-- 생년월일 유효성 검사 경고 메시지 -->
          <p v-if="birthdayError" class="text-kb-gray-2 text-sm mt-1 ml-1">
            생년월일을 YYYYMMDD 형식으로 입력해주세요.
          </p>
        </div>

        <!-- 제출 버튼 -->
        <div class="text-center">
          <button
            :disabled="!isFormValid"
            type="submit"
            class="h-[50px] w-full rounded-md text-white bg-kb-brown-1 hover:focus:ring-kb-yellow-1 focus:bg-kb-yellow-1 transition-colors duration-150"
            :class="{
              'opacity-50': !isFormValid,
              'cursor-not-allowed': !isFormValid,
            }"
          >
            비밀번호 찾기
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const name = ref('');
const tel = ref('');
const birthday = ref('');

// 전화번호 유효성 검사
const telError = computed(() => {
  return !/^\d{10,11}$/.test(tel.value);
});

// 생년월일 유효성 검사
const birthdayError = computed(() => {
  return !/^\d{8}$/.test(birthday.value);
});

// 모든 필드가 유효한지 확인하는 computed
const isFormValid = computed(() => {
  return name.value && !telError.value && !birthdayError.value;
});

const handleSubmit = () => {
  if (isFormValid.value) {
    alert('비밀번호 찾기 요청이 완료되었습니다.');
  } else {
    alert('모든 필드를 올바르게 입력해주세요.');
  }
};
</script>
