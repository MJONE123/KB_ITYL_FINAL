<template>
    <div class="h-[70px] mb-[120px]"></div>
    <div class="flex">
      <SideBar/>
      <div>
        <p class="text-[20px] text-font-color ml-[150px] mb-[30px]">회원탈퇴</p>
  
        <!-- 비밀번호 입력 -->
        <div class="justify-center ml-[150px]">
          <label for="password" class="text-font-color mb-1 px-3">비밀번호를 입력해주세요</label>
          <input
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호를 입력해주세요"
            class="text-font-color pl-4 h-[50px] w-[500px] rounded-md border border-kb-gray-2 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
          />
        </div>
  
        <!-- 약관 동의 -->
        <div class="flex justify-center ml-[150px] mt-[50px] w-[950px] h-[250px] rounded-[15px] border-2">
          <p class="p-2 mr-[180px] text-kb-gray-1 text-[16px]">
            <br><br>
            회원 탈퇴시 개인 정보 및 Allabo에서 만들어진 모든 데이터는 삭제됩니다.<br>
            (단, 아래 항목은 표기된 법률에 따라 특정 기간 동안 보관됩니다.)<br>
            <br>
            사용자의 불만 또는 분쟁처리에 관한 기록 보존 이유: 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간: 5년<br>
            신용정보의 수집, 처리 및 이용 등에 관한 기록 보존 이유: 신용 정보의 이용 및 보호에 관한 법률 보존 기간: 3년 
          </p>
        </div>
  
        <p class="text-[16px] text-kb-gray-2 ml-[150px] mt-[20px]">유의 사항</p>
        <div class="flex ml-[150px] mt-[10px] w-[950px] h-[100px] rounded-[15px] border-2">
          <p class="px-8 text-kb-gray-1 text-[16px]">
            <br>
            회원 탈퇴 처리 후에는 회원님의 개인정보를 복원할 수 없으며, 회원탈퇴 진행 시 해당 이메일은 영구적으로 삭제되어 재가입이 불가능합니다.<br>
            동일한 이메일이나 휴대폰 번호로는 30일 동안 재가입 및 등록할 수 없습니다.
          </p>
        </div>
  
        <!-- 체크박스 -->
        <div class="flex items-center ml-[180px] mt-[20px]">
          <input type="checkbox" v-model="checked" class="mr-2">
          <p class="text-kb-gray-2 text-[15px]">해당 내용을 모두 확인했으며, 회원 탈퇴에 동의합니다.</p>    
        </div>
  
        <!-- 버튼 -->
        <div class="flex justify-center my-[50px] ml-[80px] ">
          <button @click="$router.push('/login')"
                  class="h-[50px] w-[150px] rounded-md bg-white text-kb-brown-2 border border-kb-brown-2 mr-4 hover:bg-gray-100 transition duration-200">취소</button>
          <button :class="[isFormValid ? 'bg-kb-brown-2 hover:bg-kb-yellow-1' : 'bg-kb-gray-2']" 
                  :disabled="!isFormValid" 
                  class="h-[50px] w-[150px] rounded-md text-white transition duration-200">회원 탈퇴</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  import SideBar from './SideBar.vue';
  import { useRouter } from 'vue-router';

  const password = ref('');
  const checked = ref(false);

  const router=useRouter();

  const isFormValid = computed(() => {
    return password.value.trim() !== '' && checked.value;
  });
  </script>
  