<template>
  <div class="firebase-sms-auth">
    <!--전화번호 입력 칸 -->
    <div class="mt-1">
      <div class="flex space-x-2">
        <select
          class="mr-1 text-font-color pl-2 h-[50px] w-[80px] rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
        >
          <option value="0">통신사</option>
          <option value="1">skt</option>
          <option value="2">kt</option>
          <option value="3">lg u+</option>
        </select>
        <input
          v-model="phoneNumber"
          type="text"
          placeholder="전화번호 입력( - 없이 입력해주세요)"
          class="ml-2 pl-4 text-font-color h-[50px] w-[260px] rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
        />
        <button
          @click="sendCode"
          :disabled="!recaptchaSolved"
          class="bg-kb-brown-1 w-[120px] text-white rounded-md h-[50px] hover:text-font-color hover:bg-kb-yellow-1"
        >
          인증코드 전송
        </button>
      </div>

      <!-- <div>
      
      </div> -->
    </div>
    <!--에러메세지-->
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div class="my-4" id="recaptcha-container" ref="recaptchaContainer"></div>

    <!--인증코드-->
    <label for="verificationCode" class="text-font-color">인증코드</label>
    <div class="mt-1 flex space-x-2 mb-6">
      <div>
        <input
          v-model="verificationCode"
          type="text"
          placeholder="인증코드 입력"
          class="pl-4 text-font-color h-[50px] w-[350px] rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-kb-brown-2 transition duration-200"
        />
      </div>
      <div>
        <button
          @click="verifyCode"
          class="bg-kb-brown-1 w-[120px] text-white rounded-md h-[50px] hover:text-font-color hover:bg-kb-yellow-1"
        >
          인증코드 확인
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getApps, initializeApp } from "firebase/app";
import {
  getAuth,
  RecaptchaVerifier,
  signInWithPhoneNumber,
} from "firebase/auth";
import { firebaseConfig } from "../../apis/utils/firebase";

// emit을 정의합니다
const emit = defineEmits(["verification-complete"]);

let app;
if (!getApps().length) {
  app = initializeApp(firebaseConfig);
} else {
  app = getApps();
}

const auth = getAuth();
auth.languageCode = "ko";

const phoneNumber = ref("");
const verificationCode = ref("");
const errorMessage = ref("");
const recaptchaSolved = ref(false);
let recaptchaVerifier = null;
let confirmationResult = null;
const recaptchaContainer = ref(null);

// Recaptcha 설정 함수
const setUpRecaptcha = async () => {
  try {
    recaptchaVerifier = new RecaptchaVerifier(auth, recaptchaContainer.value, {
      size: "normal",
      callback: () => {
        recaptchaSolved.value = true;
      },
      "expired-callback": () => {
        recaptchaSolved.value = false;
      },
    });
    await recaptchaVerifier.render();
  } catch (error) {
    console.error("Error setting up reCAPTCHA:", error);
    errorMessage.value = "reCAPTCHA 설정 오류: " + error.message;
  }
};

// 인증 코드 전송 함수
const sendCode = async () => {
  const phoneNumberRegex = /^010\d{8}$/; // 전화번호 형식 검증
  if (!phoneNumberRegex.test(phoneNumber.value)) {
    errorMessage.value =
      "전화번호 형식이 잘못되었습니다. (010-xxxx-xxxx 형식으로 입력하세요)";
    return;
  }

  try {
    const formattedPhoneNumber = `+82${phoneNumber.value.slice(1)}`; // +82 형식으로 변환
    confirmationResult = await signInWithPhoneNumber(
      auth,
      formattedPhoneNumber,
      recaptchaVerifier
    );
    alert("인증 코드가 전송되었습니다. 메시지를 확인해 주세요.");
    errorMessage.value = ""; // 오류 메시지 초기화

    // 전화번호를 부모로 전달
    emit("verification-complete", phoneNumber.value);
  } catch (error) {
    console.error("Error sending SMS:", error);
    errorMessage.value = `SMS 전송 실패: ${error.message}`;

    if (error.code === "auth/captcha-check-failed") {
      await setUpRecaptcha(); // reCAPTCHA 오류 시 재설정
    }
  }
};

// 인증 코드 확인 함수
const verifyCode = async () => {
  try {
    const result = await confirmationResult.confirm(verificationCode.value);
    console.log("User signed in:", result.user);
    alert("인증 완료");
    errorMessage.value = ""; // 오류 메시지 초기화
  } catch (error) {
    console.error("Error verifying code:", error);
    errorMessage.value = "인증 코드 확인 실패: " + error.message;
  }
};

onMounted(() => {
  setUpRecaptcha();
});
</script>

<style scoped>
.error-message {
  color: red;
}

#recaptcha-container {
  width: 100px;
}
</style>
