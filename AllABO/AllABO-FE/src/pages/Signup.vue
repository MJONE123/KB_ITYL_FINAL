<template>
  <div class="flex flex-col h-screen">
    <div class="h-[40vh] bg-white flex flex-col items-center justify-center">
      <div class="flex justify-center mt-[50px]"></div>
    </div>

    <div class="h-[60vh] bg-white flex flex-col items-center">
      <!-- 회원가입 입력 폼 -->
      <div class="w-full max-w-lg px-6">
        <form @submit.prevent="submitForm">
          <!-- 이름 -->
          <div class="mt-[50px] flex flex-col">
            <label for="name" class="mb-1 text-font-color">이름</label>
            <input
              type="text"
              id="name"
              v-model="name"
              placeholder="이름을 입력해주세요"
              class="text-font-color pl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
            />
          </div>

          <!-- 생년월일 -->
          <div class="flex flex-col mt-6">
            <label for="birthday1" class="mb-1 text-font-color">생년월일</label>
            <div class="flex">
              <input
                type="text"
                id="birthday1"
                v-model="birthday1"
                placeholder="주민번호 앞6자리 "
                class="text-font-color pl-4 h-[50px] w-1/2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
              />
              <p class="mx-4 mt-2 text-lg text-font-color">-</p>
              <input
                type="password"
                id="birthday2"
                v-model="birthday2"
                placeholder="주민번호 뒷자리"
                class="text-font-color pl-4 h-[50px] w-1/2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
              />
            </div>
          </div>

          <!-- 전화번호 인증 -->
          <div class="flex flex-col mt-6">
            <label for="phoneNumber" class="mb-1 text-font-color"
              >전화번호</label
            >
            <AuthenticationBtn
              @verification-complete="handlePhoneVerification"
            />
          </div>

          <!-- 이메일 -->
          <label for="id" class="mt-2 text-font-color">이메일</label>
          <div class="flex mt-1 space-x-2">
            <div>
              <input
                type="email"
                id="id"
                v-model="id"
                :disabled="email"
                placeholder="이메일을 입력해주세요"
                class="text-font-color pl-4 h-[50px] w-[350px] rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
              />
              <p
                v-if="emailError && emailTouched"
                class="mt-2 text-sm text-red-500"
              >
                이메일 형식에 맞게 입력해주세요.
              </p>
            </div>
            <div>
              <button
                @click="emailckForm"
                type="button"
                :disabled="email"
                class="bg-kb-brown-1 w-[120px] text-white rounded-md h-[50px] hover:text-font-color hover:bg-kb-yellow-1"
              >
                이메일 중복확인
              </button>
            </div>
          </div>
          <p v-if="emailChkMessage" class="ml-2 text-sm text-red-500">
            {{ emailChkMessage }}
          </p>

          <!-- 비밀번호 -->
          <div class="flex flex-col mt-6">
            <label for="password" class="mb-1 text-font-color">비밀번호</label>
            <input
              type="password"
              id="password"
              v-model="password"
              placeholder="비밀번호를 입력해주세요"
              class="text-font-color pl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
            />
            <p v-if="passwordError" class="pl-3 text-sm text-kb-gray-2">
              비밀번호는 특수문자, 영문, 숫자를 포함하여 8~12자로 설정해주세요.
            </p>
          </div>

          <!-- 비밀번호 확인 -->
          <div class="flex flex-col mt-6">
            <label for="passwordConfirm" class="mb-1 text-font-color"
              >비밀번호 확인</label
            >
            <input
              type="password"
              id="passwordConfirm"
              v-model="passwordConfirm"
              placeholder="비밀번호를 다시 입력해주세요"
              class="pl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
            />
            <p v-if="passwordConfirmError" class="mt-1 text-sm text-red-500">
              비밀번호가 일치하지 않습니다.
            </p>
          </div>

          <!-- 연 소득 -->
          <div class="flex flex-col mt-6">
            <label for="asset" class="mb-1 text-font-color">연 소득</label>
            <input
              type="number"
              id="asset"
              v-model="asset"
              placeholder="연 소득을 입력해주세요 (예: 30000000)"
              class="pl-4 text-font-colorpl-4 h-[50px] w-full rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
            />
          </div>

          <!-- 가입하기 버튼 -->
          <div class="flex justify-center mt-[50px] mb-[50px]">
            <button
              @click="$router.push('/login')"
              class="h-[50px] w-[150px] rounded-md bg-white text-kb-brown-2 border border-kb-brown-2 mr-4 hover:bg-gray-100 transition duration-200"
            >
              취소
            </button>
            <button
              @click="submitForm"
              type="button"
              :class="submitButtonClass"
              class="h-[50px] w-[150px] rounded-md bg-kb-brown-2 text-white transition duration-200 hover:bg-kb-yellow-1"
            >
              가입하기
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, computed, watch, onBeforeMount } from "vue";
import { useRoute, useRouter } from "vue-router";
import AuthenticationBtn from "../components/Login/AuthenticationBtn.vue";

const route = useRoute();
const email = route.query.email;


const name = ref("");
const birthday1 = ref("");
const birthday2 = ref("");
const id = ref("");
const password = ref("");
const passwordConfirm = ref("");
const phoneNumber = ref("");
const asset = ref("");
const emailTouched = ref(false);
const isVerified = ref(false);
const emailChkMessage = ref("");
const isFormValid = computed(() => {
  return (
    name.value &&
    birthday1.value &&
    birthday2.value.length === 7 &&
    password.value &&
    passwordConfirm.value &&
    asset.value
  );
});

onBeforeMount(()=>{
  if(email){
    id.value=email;
  }
})

const router = useRouter();

// 주민번호 뒷자리 7자리 제한
watch(birthday2, (newValue) => {
  if (newValue.length > 7) {
    birthday2.value = newValue.slice(0, 7);
  }
});

// 주민번호를 합치는 함수
const birthday = computed(() => {
  return birthday1.value && birthday2.value
    ? `${birthday1.value}-${birthday2.value}`
    : "";
});

// 이메일 유효성 검사
const emailError = computed(() => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return !emailRegex.test(id.value);
});

// 비밀번호 유효성 검사
const passwordError = computed(() => {
  const passwordRegex =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!#*])[A-Za-z\d~!#*]{8,12}$/;
  return !passwordRegex.test(password.value);
});

// 비밀번호 확인 유효성 검사
const passwordConfirmError = computed(() => {
  return password.value !== passwordConfirm.value;
});

// 전화번호 인증 완료 핸들러
function handlePhoneVerification(phone) {
  phoneNumber.value = phone;
  console.log("인증된 전화번호:", phone);
  isVerified.value = true;
}

async function submitForm() {
  if (isFormValid.value) {
    try {
      const response = await axios.post(
        `http://localhost:8080/signup`,
        {
          id: id.value,
          pwd: password.value,
          name: name.value,
          identityNumber: birthday.value,
          phoneNumber: phoneNumber.value,
          totalIncome: asset.value,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      router.push({ name: "SignSuccess", params: { userData: response.data } });
    } catch (error) {
      alert("서버와의 통신에 문제가 발생했습니다.");
    }
  }
}

// 이메일 중복 확인 함수
async function emailckForm() {
  try {
    const response = await axios.get(
      `http://localhost:8080/iddupchk/${id.value}`
    );
    if (response.data === "이미 존재하는 아이디입니다") {
      emailChkMessage.value = "이미 사용중인 아이디 입니다.";
    } else {
      emailChkMessage.value = "사용 가능한 아이디 입니다.";
    }
    console.log(response);
  } catch (error) {
    alert("서버와의 통신에 문제가 발생했습니다.");
  }
}

function handleEmailBlur() {
  emailTouched.value = true;
}
</script>

<style scoped>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
