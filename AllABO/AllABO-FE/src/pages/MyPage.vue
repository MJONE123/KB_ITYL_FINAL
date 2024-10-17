<template>
  <div class="h-[320px] bg-kb-yellow-4"></div>
  <!-- 마이페이지-->
  <div class="flex justify-center">
    <div class="w-full max-w-5xl">
      <div class="flex items-center mb-[30px]">
        <div class="flex flex-col">
          <img :src="profileImage" class="w-40 h-40 rounded-full mb-[10px]" alt="user profile" />
          <button @click="$router.push('/mypage-profile')" class="underline text-kb-gray-2">내 정보 수정</button>
        </div>

        <div class="ml-[40px] flex flex-col justify-center">
          <div class="flex items-end">
            <p class="text-4xl font-semibold text-font-color">
              {{ authStore.name }}
            </p>
            <button class="text-kb-gray-2 ml-[22px]" @click="handleLogout">로그아웃</button>
          </div>
          <div class="flex items-center mt-2">
            <p class="text-[20px] text-font-color">{{ authStore.id }}</p>
          </div>
        </div>
      </div>
      <LikeProducts :favoriteData="favoriteData" />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "../stores/auth";
import LikeProducts from "../components/MyPage/LikeProducts.vue";
import { onBeforeMount } from "vue";
import { getMyDetailFavoritesList } from "../apis/api/favorites";
import { checkCardMapping, creditCardMapping, depositMapping, insuranceMapping, loanMapping } from "../servies/mappingData";

const profileImage = ref(sessionStorage.getItem("profileImage") || "/images/Mypage/user.png");
const router = useRouter();
const authStore = useAuthStore();
const favoriteData = ref([]);

const handleLogout = () => {
  alert("로그아웃 되었습니다.");
  authStore.logout();
  router.push("/");
};

onBeforeMount(async () => {
  const response = await getMyDetailFavoritesList();

  let temp = [];
  for (let d of response.data) {
    switch (d.productId) {
      case "A01":
        temp = depositMapping([d]);
        break;
      case "A02":
        temp = loanMapping([d]);
        break;
      case "A03":
        temp = insuranceMapping([d]);
        break;
      case "A04":
        temp = checkCardMapping([d]);
        break;
      case "A05":
        temp = creditCardMapping([d]);
        break;
    }
    temp[0].isFavorite=true

    favoriteData.value.push(...temp);
  }
});
</script>
