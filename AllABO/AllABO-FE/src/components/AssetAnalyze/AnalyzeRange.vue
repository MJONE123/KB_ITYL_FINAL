<template>
  <div class="flex flex-col items-center w-full">
    <p class="text-[32px] text-font-color font-semibold">분위별 분석 결과</p>
    <div class="bg-gray-600 h-0.5 w-[210px]"></div>
  </div>

  <div class="flex justify-center px-10 mx-auto space-x-14">
    <!-- 왼쪽 원형 영역 -->
    <div
      class="relative flex flex-col items-center justify-center"
      ref="yellowCircle"
    >
      <div class="relative">
        <!-- 큰 노란 원 -->
        <div
          class="flex flex-col items-center justify-center rounded-full bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 shadow-lg h-[550px] w-[550px] text-center px-8"
        >
          <div class="flex flex-col items-center p-6 space-y-4">
            <div
              v-for="(tag, index) in hashtags"
              :key="index"
              class="p-4 transition-all duration-300 rounded-lg text-font-color hover:bg-kb-yellow-10"
            >
              <p class="font-semibold text-kb-brown-1 text-[24px]">{{ tag }}</p>
            </div>
          </div>
        </div>

        <!-- 분위 원 -->
        <div
          class="absolute z-10 top-[-45px] right-[55px] transform translate-x-10 translate-y-10 rounded-full bg-[#FFE1C2] shadow-md h-[150px] w-[150px] flex items-center justify-center"
        >
          <p class="font-semibold text-font-color text-center text-[32px]">
            {{ incomeRange }}
          </p>
        </div>
      </div>

      <!-- 지출 초과 원  -->
      <div
        class="absolute top-[130px] right-[-80px] rounded-full bg-kb-blue-6 shadow-md h-[180px] w-[180px] flex items-center justify-center"
      >
        <p
          class="text-[24px] font-semibold text-font-color text-center whitespace-pre-wrap"
        >
          "{{ status }}"
        </p>
      </div>
    </div>

    <!-- 텍스트 영역 -->
    <div class="flex flex-col justify-center h-[650px] w-[650px] pl-24">
      <div class="transition-all duration-500 ease-in-out grow-animation-hash">
        <div class="flex flex-col h-full space-y-4">
          <!-- 상단 2개 박스 -->
          <div class="flex items-center space-x-4 h-[30%]">
            <div
              class="w-[45%] text-center bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 p-6 rounded-3xl shadow-md flex flex-col items-center justify-center"
            >
              <p class="text-[18px] text-font-color">
                {{ authStore.name }}님의 월 소득
                <span class="text-font-color text-[22px] font-semibold"
                  >{{ formatNumber(userIncome) }}
                </span>
                원
              </p>
            </div>

            <!-- VS 요소 -->
            <div
              class="flex items-center justify-center flex-shrink-0 w-12 h-12"
            >
              <span class="text-2xl font-bold text-font-color">VS</span>
            </div>

            <div
              class="w-[45%] text-center bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 p-6 rounded-3xl shadow-md flex flex-col items-center justify-center"
            >
              <p class="text-[18px] text-font-color">
                {{ incomeRange }}평균 월 소득
                <span class="text-font-color text-[22px] font-semibold">{{
                  formatNumber(avgIncomeInGroup)
                }}</span>
                원
              </p>
            </div>
          </div>

          <!-- 중간 2개 박스 -->
          <div class="flex items-center space-x-4 h-[30%]">
            <div
              class="w-[45%] text-center bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 p-6 rounded-3xl shadow-md flex flex-col items-center justify-center"
            >
              <p class="text-[18px] text-font-color">
                {{ authStore.name }}님의 소득 대비<br />
                지출 비율
                <span class="text-font-color text-[22px] font-semibold pl-1">
                  {{ userExpenditurePercent.toFixed(1) }}%</span
                >
              </p>
            </div>

            <!-- VS 요소 -->
            <div
              class="flex items-center justify-center flex-shrink-0 w-12 h-12"
            >
              <span class="text-2xl font-bold text-font-color">VS</span>
            </div>

            <div
              class="w-[45%] text-center bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 p-6 rounded-3xl shadow-md flex flex-col items-center justify-center"
            >
              <p class="text-[18px] text-font-color">
                {{ incomeRange }} 소득 대비<br />
                지출 비율
                <span class="text-font-color text-[22px] font-semibold pl-1"
                  >{{ rangeExpenditurePercent.toFixed(1) }}%</span
                >
              </p>
            </div>
          </div>

          <div
            class="w-full bg-gradient-to-b from-kb-yellow-4 to-kb-yellow-10 p-6 rounded-3xl shadow-md h-[30%] flex flex-col justify-center"
          >
            <p class="text-[18px] text-font-color font-bold mb-2">
              [{{ status }}]
            </p>

            <p class="text-[18px] text-font-color">
              <span class="font-bold">{{ authStore.name }}</span>
              <!-- <span class="font-bold">{{ incomeRange }}분위</span>에
              속합니다.<br /> -->
              님은 소속 분위보다 상대적으로
              <span class="font-bold">{{ relativeExpenditurePercent }}%</span>
              을 사용합니다.
            </p>
            <p class="text-[18px]" :style="{ whiteSpace: 'pre-line' }">
              {{ advice }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted, computed } from 'vue';
import { useAuthStore } from '../../stores/auth';
import { loadingStateStore } from '../../stores/loadingStateStore';

const authStore = useAuthStore();

//소득 (원)
const incomeRange = ref(''); //"소득분위(n분위)"
const userIncome = ref(''); //사용자 월 소득
const avgIncomeInGroup = ref(''); //소속 분위 소득(원)

//소득별 퍼센트(%)
const userExpenditurePercent = ref(0); //사용자 월 소득 대비 지출 비율(%제외)
const rangeExpenditurePercent = ref(0); // 소속 분위 대비 지출 비율

const spendingManagementNeeded = ref(false);

const hashtags = ref([]);
const isVisible = ref(false);
const yellowCircle = ref(null);

// 숫자 정리
const formatNumber = (value) => {
  if (typeof value === 'number') {
    return Math.floor(value).toLocaleString(); // 소수점 이하 버리고 세 자리마다 콤마 추가
  }
  return value;
};

const loadingStore = loadingStateStore();

const fetchIncomeData = async () => {
  const token = authStore.token;
  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }
  loadingStore.setIsAssetAnalyzeLoading(true, 2);

  try {
    const response = await axios.post(
      `http://localhost:8080/assets/income-level`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }
    );

    if (response.status == 200) {
      loadingStore.setIsAssetAnalyzeLoading(false, 2);
    }

    const data = response.data.resultMap;
    userIncome.value = Number(data['사용자 월 소득(원)']);
    userExpenditurePercent.value = Number(
      data['사용자 월 소득 대비 지출 비율(%제외)']
    );
    avgIncomeInGroup.value = Number(data['소속 분위 소득(원)']);
    rangeExpenditurePercent.value = Number(
      data['소속 분위 월 소득 대비 지출 비율(%제외)']
    );
    incomeRange.value = data['소득분위(n분위)'];
    hashtags.value = data['#요약키워드(4개)'];
    spendingManagementNeeded.value = data['지출 관리 필요성(true or false)'];
    console.log(response);

    console.log(data);
  } catch (error) {
    console.error('데이터 가져오기 실패:', error);
  }
};

// 상대적 지출 비율 계산하기 상대적_지출_비율 = (사용자_지출_비율 / 소속_분위_지출_비율) * 100

const relativeExpenditurePercent = computed(() => {
  return (
    (userExpenditurePercent.value / rangeExpenditurePercent.value) *
    100
  ).toFixed(1);
});
const statusAndAdvice = computed(() => {
  const relative = parseFloat(relativeExpenditurePercent.value);
  let status = '';
  let advice = '';

  if (relative <= 38) {
    status = '지출 관리 시급';
    advice =
      '필수 지출과 선택 지출을 구분하고,\n 선택 지출을 줄이는 것부터 시작하세요.';
  } else if (relative >= 39 && relative <= 48) {
    status = '지출 관리 필요';
    advice = '지출 내역을 꼼꼼히 분석하고,\n 불필요한 지출을 찾아 줄여보세요.';
  } else if (relative >= 49 && relative <= 50) {
    status = '지출 관리 양호';
    advice =
      '현재의 지출 패턴을 유지하면서,\n 저축을 조금씩 늘려보는 것은 어떨까요?';
  } else if (relative >= 51 && relative <= 59) {
    status = '지출 관리 우수';
    advice =
      '잉여 자금으로 투자나 재테크를 고려해보세요.\n 장기적인 재무 목표를 세워보는 것도 좋겠습니다.';
  } else {
    status = '지출 관리\n매우 우수';
    advice =
      '현재의 지출 습관을 유지하세요.\n 다만, 과도한 절약으로 삶의 질이 떨어지지 않도록 주의하세요.';
  }

  return { status, advice };
});

// status와 advice 추출
const status = computed(() => statusAndAdvice.value.status);
const advice = computed(() => statusAndAdvice.value.advice);

const observerCallback = (entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      isVisible.value = true; // 원이 보이면 애니메이션 실행
      observer.disconnect(); // 애니메이션이 실행된 후 옵저버 해제
    }
  });
};

relativeExpenditurePercent;

let observer;

onMounted(async () => {
  if (authStore.isLoggedIn) {
    await authStore.fetchUserProfile();
  }

  fetchIncomeData();

  // Intersection Observer 초기화
  observer = new IntersectionObserver(observerCallback, {
    threshold: 0.1,
  });

  // yellowCircle 요소가 존재할 경우 옵저버에 추가합니다.
  if (yellowCircle.value) {
    observer.observe(yellowCircle.value);
  }
});
</script>

<style scoped>
/*
@keyframes grow {
  from {
    transform: scale(0);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

.grow-animation {
  animation: grow 1s ease-out forwards;
}

@keyframes grow-hash {
  from {
    transform: scale(0);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

.grow-animation-hash {
  animation: grow-hash 1s ease-out forwards;
}*/
</style>
