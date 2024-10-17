<template>
  <div class="flex flex-col h-screen">
    <!-- 페이지 상단 -->
    <div
      class="bg-kb-yellow-4 h-[47%] flex flex-col items-center justify-center px-4 py-8 relative"
    >
      <!-- 프로그레스 바 -->
      <div
        class="absolute w-full max-w-[528px] left-1/2 transform -translate-x-1/2"
        style="top: 50%"
      >
        <div class="absolute top-[-24px] left-0 text-font-color text-sm">
          {{ currentStep }} / {{ totalStep }}
        </div>
        <div class="progress-bar bg-gray-200 h-2 rounded-full overflow-hidden">
          <div
            class="bg-kb-yellow-2 h-full duration-200 ease-out"
            :style="{ width: `${progress}%` }"
          ></div>
        </div>
      </div>

      <!-- 질문 텍스트 -->
      <Transition name="fade" mode="out-in">
        <h2
          :key="currentQuestion.id"
          class="text-font-color text-3xl font-bold text-center absolute w-full max-w-[760px]"
          style="top: 66.7%"
        >
          {{ currentQuestion.text }}
        </h2>
      </Transition>

      <!-- 구분선 -->
      <div
        class="w-full max-w-[760px] h-px bg-kb-brown-2 opacity-50 absolute left-1/2 transform -translate-x-1/2"
        style="bottom: 16.6%"
      ></div>
    </div>

    <!-- 페이지 하단 -->
    <div
      class="bg-white h-[53%] overflow-hidden relative flex items-center justify-center"
    >
      <Transition
        :name="isMovingForward ? 'slide-left' : 'slide-right'"
        mode="out-in"
      >
        <div
          :key="currentQuestion.id"
          class="w-full max-w-[448px] flex items-center justify-center"
        >
          <!-- 선택지 -->
          <TransitionGroup
            :name="
              isMovingForward ? 'list-complete-left' : 'list-complete-right'
            "
            tag="div"
            :class="[containerClass, 'w-full']"
          >
            <button
              v-for="option in currentQuestion.options"
              :key="option"
              @click="selectOption(option, currentQuestion)"
              class="py-3 px-6 bg-white border text-font-color rounded-[15px] shadow-md transition hover:bg-[#F4F4F5] duration-300"
            >
              {{ option }}
            </button>
          </TransitionGroup>
        </div>
      </Transition>

      <!-- 이전 버튼 -->
      <button
        v-if="currentStep > 1"
        @click="goToPreviousQuestion"
        class="absolute left-[20%] top-1/2 transform -translate-y-1/2 p-2 rounded-full focus:outline-none"
      >
        <div
          class="absolute inset-0 bg-kb-yellow-2 opacity-60 rounded-full blur-sm"
        ></div>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="#FFFFFF"
          class="size-14 pr-1.5 relative z-10"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M15.75 19.5 8.25 12l7.5-7.5"
          />
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import {
  insuranceSurvey,
  cardSurvey,
  loanSurvey,
  depositsSurvey,
} from '../constant/surveyData';
import { postSurvey } from '../apis/api/survey';

const route = useRoute();
const router = useRouter();

// 프로그레스
const currentStep = ref(1);
const totalStep = computed(() => {
  const category = route.query.category;

  switch (category) {
    case '카드':
      return 4;
    case '예/적금':
      return 5;
    case '대출':
      return 3;
    case '보험':
      return 4;
  }
});

const progress = computed(() => (currentStep.value / totalStep.value) * 100);

const categoryUrl = ref('');

// 카테고리 선택에 따라 설문 데이터 결정
const surveyData = computed(() => {
  const category = route.query.category;

  switch (category) {
    case '카드':
      categoryUrl.value = '/card';
      return cardSurvey;
    case '예/적금':
      categoryUrl.value = '/deposit';
      return depositsSurvey;
    case '대출':
      categoryUrl.value = '/loan';
      return loanSurvey;
    case '보험':
      categoryUrl.value = '/insurance';
      return insuranceSurvey;
  }
});

const questionHistory = ref([]);
const selectedOptions = ref([]);
const isMovingForward = ref(true);

const currentQuestion = computed(() => {
  return (
    surveyData.value[questionHistory.value[questionHistory.value.length - 1]] ||
    {}
  );
});

// 선택지 개수에 따른 css 변경
const containerClass = computed(() => {
  const optionsCount = currentQuestion.value.options?.length || 0;
  return optionsCount > 5
    ? 'grid grid-cols-2 gap-6'
    : 'flex flex-col space-y-6';
});

function initializeSurvey() {
  currentStep.value = 1;
  questionHistory.value = [0]; // Start with the first question
  selectedOptions.value = [];
}

watch(
  () => route.query.category,
  () => {
    initializeSurvey();
  },
  { immediate: true }
);

function selectOption(option, question) {
  const optionIndex = question.options.indexOf(option);
  selectedOptions.value.push([option, optionIndex]);
  currentStep.value++;
  isMovingForward.value = true;
  // console.log(selectedOptions.value);

  if (currentStep.value > totalStep.value) {
    sendData();
    return;
  }

  const nextQuestionId =
    typeof question.nextQuestion === 'function'
      ? question.nextQuestion(option)
      : question.nextQuestion;

  if (nextQuestionId !== null) {
    const nextQuestionIndex = surveyData.value.findIndex(
      (q) => q.id === nextQuestionId
    );
    if (nextQuestionIndex !== -1) {
      questionHistory.value.push(nextQuestionIndex);
    }
  }
}

function goToPreviousQuestion() {
  if (questionHistory.value.length > 1) {
    questionHistory.value.pop(); // Remove the current question
    selectedOptions.value.pop(); // 마지막 선택 제거
    currentStep.value--;
    isMovingForward.value = false;
  }
}

async function sendData() {
  try {
    const response = await postSurvey(selectedOptions.value, categoryUrl.value);
    // console.log(response.data);

    if (response.status === 200) {
      router.push({
        path: '/all-products',
        query: {
          recommend: JSON.stringify(response.data),
          category: route.query.category,
        },
      });
    } else {
      // 알림창? 설문 다시?
    }
  } catch (e) {
    throw new Error('Survey Err: ', e);
  }
}
</script>

<style scoped>
.scrollbar-hide {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.scrollbar-hide::-webkit-scrollbar {
  display: none;
}

.progress-bar {
  border-radius: 40px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active {
  transition: all 0.3s ease-out;
}

.slide-left-enter-from {
  opacity: 0;
  transform: translateX(20px);
}

.slide-left-leave-to {
  opacity: 0;
  transform: translateX(-20px);
}

.slide-right-enter-from {
  opacity: 0;
  transform: translateX(-20px);
}

.slide-right-leave-to {
  opacity: 0;
  transform: translateX(20px);
}

.list-complete-left-enter-active,
.list-complete-left-leave-active,
.list-complete-right-enter-active,
.list-complete-right-leave-active {
  transition: all 0.3s ease;
}

.list-complete-left-enter-from,
.list-complete-right-enter-from {
  opacity: 0;
  transform: translateX(30px);
}

.list-complete-left-leave-to,
.list-complete-right-leave-to {
  opacity: 0;
  transform: translateX(-30px);
}
</style>
