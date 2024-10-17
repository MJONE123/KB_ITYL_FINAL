<template>
  <header>
    <nav
      :class="[
        'fixed top-0 left-0 w-full h-[70px] z-50',
        isScrolledOrHovered ? 'bg-white shadow-lg' : 'bg-transparent',
        'transition duration-500 ease-in-out',
      ]"
      @mouseenter="handleMouseEnter"
      @mouseleave="handleMouseLeave"
    >
      <div class="mx-[340px] h-full flex items-center justify-between">
        <!-- 로고 -->
        <router-link to="/" class="text-2xl font-bold" @click="handleNavClick">
          F:YL
        </router-link>

        <!-- 네비게이션 메뉴와 프로필 아이콘을 포함하는 컨테이너 -->
        <div class="flex items-center">
          <!-- 네비게이션 간격 조정 -->
          <!-- 네비게이션 메뉴 -->
          <ul class="flex h-[70px] items-center">
            <!-- 아이템 간 간격 줄이기 -->
            <li
              v-for="item in navItems"
              :key="item.route"
              class="flex flex-col h-[70px]"
            >
              <router-link
                :to="item.route"
                class="h-[70px] flex items-center px-4 hover:underline hover:text-kb-yellow-1 hover:decoration-kb-yellow-1"
              >
                {{ item.name }}
              </router-link>
            </li>

            <p class="mx-1">|</p>

            <!-- 로그인 상태에 따라 동적 UI -->
            <li class="flex flex-col h-[70px]">
              <router-link
                v-if="!authStore.isLoggedIn"
                to="/login"
                class="h-[70px] flex items-center px-4 hover:underline hover:text-kb-yellow-1 hover:decoration-kb-yellow-1"
              >
                로그인
              </router-link>
              <!-- 로그인된 상태일 때 사용자 이름을 표시 -->
              <button
                v-else
                @click="goToMypage"
                class="h-[70px] flex items-center px-4 hover:underline hover:text-kb-yellow-1 hover:decoration-kb-yellow-1"
              >
                {{ authStore.name }}님
              </button>
            </li>
          </ul>

          <!-- 프로필 아이콘_마이페이지 이동 -->
          <!-- 로그인 상태에 따라 클릭 여부 결정 -->
          <div>
            <!-- 로그인되지 않은 상태에서만 프로필 클릭 가능 -->
            <router-link
              v-if="!authStore.isLoggedIn"
              to="/mypage"
              :class="[
                isScrolledOrHovered ? 'text-font-color' : 'text-font-color',
              ]"
              class="hover:text-kb-brown-1"
            >
              <img
                src="/images/Mypage/user.png"
                class="w-6 h-6"
                alt="프로필 아이콘"
              />
            </router-link>
            <!-- 로그인된 상태에서는 클릭 불가한 이미지 -->
            <img
              v-else
              :src="profileImage"
              class="w-6 h-6 cursor-default"
              alt="프로필 이미지"
            />
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { useHeaderStore } from '../stores/headerStore';
import { useAuthStore } from '../stores/auth';
import { useRouter, useRoute } from 'vue-router'; // 라우터 사용
import { ref, onMounted, onUnmounted, computed, watchEffect, watch } from 'vue';
import { profileStateStore } from '../stores/profileStore';

const profileImage = ref(
  sessionStorage.getItem('profileImage') || '/images/Mypage/user.png'
);

const headerStore = useHeaderStore();
const authStore = useAuthStore(); // authStore 사용
const router = useRouter(); // 라우터 사용
const route = useRoute();
const profileStore = profileStateStore();

const navItems = [
  { name: '맞춤 상품', route: '/products' },
  { name: '자산 분석', route: '/asset-detail' },
  { name: '자산 설계', route: '/asset-plan' },
];

const isScrolledOrHovered = computed(() => headerStore.isActive);

// 스크롤 상태 핸들링
function handleScroll() {
  headerStore.setScrolled(window.scrollY > 1);
}

// 마우스 엔터/리브 핸들링
function handleMouseEnter() {
  headerStore.setHovered(true);
}

function handleMouseLeave() {
  headerStore.setHovered(false);
}

// 마이페이지로 이동하는 함수
const goToMypage = () => {
  router.push('/mypage');
};

const handleNavClick = (event) => {
  if (route.path === event.target.getAttribute('href')) {
    event.preventDefault();
    window.scrollTo({ top: 0, behavior: 'smooth' });
    headerStore.setScrolled(false);
  }
};

// 세션 스토리지에 저장된 이미지가 변경될 때마다 실시간으로 반영
watchEffect(() => {
  profileImage.value =
    sessionStorage.getItem('profileImage') || '/images/Mypage/user.png';
});

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
  headerStore.resetState();
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});

watch(profileStore.getImageUrl, () => {
  profileImage.value =
    sessionStorage.getItem('profileImage') || '/images/Mypage/user.png';
});
</script>

<style scoped>
button {
  background: none;
  border: none;
  cursor: pointer;
}
</style>
