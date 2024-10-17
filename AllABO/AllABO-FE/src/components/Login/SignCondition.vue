<template>
  <div class="h-screen flex flex-col">
    <div class="fixed w-full h-[35vh] bg-kb-yellow-4 flex flex-col items-center justify-center">
      <div class="h-[70px]"></div>
      <div class="flex justify-center mt-[30px]">
        <div class="text-3xl font-bold text-kb-brown-1">회원가입</div>
        <div class="h-[80px] bg-kb-brown-1"></div>
      </div>
      <div class="">
        <div class="flex justify-center mt-[25px]">
          <!-- btn클릭 따라 똥골 이미지 변경 -->
          <img alt="circle" :src="currentStep >= 1 ? fullCircle : emptyCircle" class="mx-7 w-5 h-5" />
          <img alt="circle" :src="currentStep >= 2 ? fullCircle : emptyCircle" class="mx-7 w-5 h-5" />
          <img alt="circle" :src="currentStep >= 3 ? fullCircle : emptyCircle" class="mx-7 w-5 h-5" />
        </div>
        <div class="flex justify-center mt-[20px] text-kb-brown-1">
          <p class="mx-4 text-[14px]font-semibold">약관동의</p>
          <p class="mx-4 text-[14px]">정보입력</p>
          <p class="mx-4 text-[14px] mb-[10px]">회원가입</p>
        </div>
      </div>
    </div>
    <div class="mt-[250px]"></div>
    <div class="mt-[90px] mb-3 flex justify-start mx-auto w-[760px]">
      <p class="text-kb-yellow-2 text-lg font-bold px-3">[필수]</p>
      <p class="text-font-color text-lg font-bold"> 개인정보 수집 및 이용 동의 </p>
    </div>
    
    <div>
      <div class="flex justify-center mx-auto w-[760px] h-[380px] rounded-[15px] border-2 ">
        <p class="p-2">
          <br><br>
          (주)000 는 아래의 목적으로 개인정보를 수집 및 이용하며, 회원의 개인정보를 안전하게 취급하는데
          최선을 다합니다. <br><br>
          <b>개인정보 수집 및 이용에 대한 안내</b><br><br>
          1. 목적 : 금융거래 인증, 금융제공<br>
          2. 항목 : 아이디, 비밀번호, 이름,주민번호, 휴대폰번호<br>
          3. 보유기간 : 회원 탈퇴 시까지 보유 (개인 신상정보를 삭제한 지원이력 정보는 일방향 암호화하여 보관됩니다.)<br><br>
          위 개인정보 수집에 대한 동의를 거부할 권리가 있으며, 동의 거부 시에는 지원자 등록이 제한될 수 있습니다. 더 자세한 내용에 대해서는  Allabo의 개인정보처리방침을 참고하시길 바랍니다.
        </p>
      </div>
      
      <div class="flex justify-center mt-[20px] mb-[90px]">
        <button @click="scrollToSignup" type="button"
          class="w-[213px] h-[50px] rounded-[15px] bg-kb-brown-2 text-white hover:bg-kb-yellow-1 focus:bg-kb-yellow-1 mb-[40px]">
          동의하기
        </button>
      </div>
    </div>

    <div ref="signupSection">
      <Signup />
    </div>
  </div>
</template>

<script setup>
import { ref,onMounted, onUnmounted } from 'vue';
import Signup from '../../pages/Signup.vue';

const fullCircle = '/images/Signup/full_circle.png';
const emptyCircle = '/images/Signup/empty_circle.png';

const currentStep = ref(1);
const signupSection = ref(null);

// btn 클릭 전까지 스크롤 활성, 비활성화
function disableScroll() {
  document.body.style.overflow = 'hidden';
}
function enableScroll() {
  document.body.style.overflow = 'auto';
}
// 동의하기 버튼 클릭 시 
const scrollToSignup = () => {
  currentStep.value = 2;
  enableScroll(); 
  signupSection.value.scrollIntoView({ behavior: 'smooth' });
};
onMounted(() => {
  disableScroll();
});
onUnmounted(() => {
  enableScroll();
});
</script>
