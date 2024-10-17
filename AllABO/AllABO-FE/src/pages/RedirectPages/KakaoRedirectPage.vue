<template>
  <div class="h-[70px]"></div>
  <div>카카오 로그인중...</div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { kakaoLoginRequestTokenHandler } from "../../apis/api/kakao";
import { onMounted } from "vue";
import { idDupChk, login } from "../../apis/api/user";

const route = useRoute();
const router = useRouter();

onMounted(() => {
  const kakaoLogin = async () => {
    const result = await kakaoLoginRequestTokenHandler(route.query.code);
    console.log(result);
    const email = result.data.kakao_account.email;

    const idDupChkResult = await idDupChk(email);
    console.log(idDupChkResult);
    if (idDupChkResult.data === "이미 존재하는 아이디입니다") {
      const result = await login(email, "rltk987548.");
      console.log(result);
      localStorage.setItem("accessToken", result.data.accessToken);
      router.push("/");
    } else {
      router.push({ path: "/signup-condition", query: { email: email } });
    }
  };
  kakaoLogin();
});
</script>
