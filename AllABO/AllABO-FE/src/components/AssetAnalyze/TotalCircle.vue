<template>
  <div class="relative w-1/2 h-[500px] flex items-center justify-center">
    <!-- 큰 원 테두리 -->
    <div
      class="absolute w-[500px] h-[500px] rounded-full border-2 border-kb-gray-2"
    ></div>

    <!-- 로그인 후 바뀌는 부분 -->
    <div
      class="absolute w-[200px] h-[200px] flex flex-col items-center justify-center"
    >
      <div class="w-[200px] h-[200px] rounded-full overflow-hidden">
        <img
          src="/images/Mypage/user3.png"
          alt="User Image"
          class="w-full h-full object-cover"
        />
      </div>
      <p class="text-center text-lg font-bold mt-2 whitespace-nowrap">
        <span class="text-kb-blue-3 text-[24px]">{{ authStore.name }}</span
        >님의 포트폴리오
      </p>
    </div>

    <!-- 작은 원들 -->
    <div
      class="absolute w-[155px] h-[155px] bg-kb-blue-2 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(0)"
    >
      <p class="text-lg">연 소득 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(totalIncome) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-yellow-6 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(1)"
    >
      <p class="text-lg">계좌 자산</p>
      <p class="text-xl font-bold">{{ formatNumber(totalAssets) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-yellow-5 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(2)"
    >
      <p class="text-lg">연금 준비 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(totalSavings) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-pink-2 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(3)"
    >
      <p class="text-lg">보험 납부 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(totalInsurance) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-pink-1 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(4)"
    >
      <p class="text-lg">투자 평가 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(totalInvestment) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-green-1 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(5)"
    >
      <p class="text-lg">카드 이용 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(monthExpenses) }}만원</p>
    </div>
    <div
      class="absolute w-[155px] h-[155px] bg-kb-blue-1 rounded-full flex items-center justify-center flex-col text-center text-font-color"
      :style="getPosition(6)"
    >
      <p class="text-lg">부채 금액</p>
      <p class="text-xl font-bold">{{ formatNumber(totalLoan) }}만원</p>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useAuthStore } from '../../stores/auth';

const authStore = useAuthStore();

const totalIncome = ref(0);
const totalAssets = ref(0);
const totalSavings = ref(0); // 연금
const totalInsurance = ref(5); // 예시 데이터로 유지
const totalInvestment = ref(0);
const monthExpenses = ref(0);
const totalLoan = ref(0);

const getPosition = (index) => {
  const angleOffset = -Math.PI / 2;
  const angle = (index / 7) * 2 * Math.PI + angleOffset;
  const radius = 250; // 반지름 조정
  const x = radius * Math.cos(angle);
  const y = radius * Math.sin(angle);

  return {
    transform: `translate(${x}px, ${y}px)`,
  };
};

// 숫자 포맷팅 함수
// const formatNumber = (value) => {
//   return new Intl.NumberFormat().format(value);
// };

const formatNumber = (value) => {
  return new Intl.NumberFormat().format(Math.floor(value)); // 소수점 제거
};

// axios 연결
const totalAssetData = async () => {
  const token = authStore.token;
  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }

  try {
    const response = await axios.get(`http://localhost:8080/assets/portfolio`, {
      headers: {
        Authorization: `Bearer ${token}`, // 헤더 정보
        'Content-Type': 'application/json',
      },
    });

    // 응답 데이터 구조에 맞게 데이터 가져오기
    const data = response.data;
    if (!data) {
      throw new Error('유효한 데이터를 가져오지 못했습니다.');
    }

    // API에서 제공하는 값들로 상태 업데이트
    totalIncome.value = data.totalIncome / 10000; // 만원 단위로 환산
    totalAssets.value = data.totalAssets / 10000;
    totalSavings.value = data.totalSavings / 10000;
    totalInvestment.value = data.totalInvestment / 10000;
    monthExpenses.value = data.monthExpenses / 10000;
    totalLoan.value = data.totalLoan / 10000;

    console, log(totalIncome.value);
  } catch (error) {
    console.error('데이터를 불러오는 중 오류가 발생했습니다:', error);
  }
};

onMounted(async () => {
  // 첫 번째 작업: 로그인 상태 확인 후 사용자 프로필 정보 가져오기
  if (authStore.isLoggedIn) {
    await authStore.fetchUserProfile();
  }

  // 두 번째 작업: 자산 데이터 가져오기
  totalAssetData();
});
</script>
