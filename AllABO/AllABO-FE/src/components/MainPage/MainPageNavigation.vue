<template>
  <div
    class="flex items-center justify-center h-screen w-screen bg-kb-yellow-4"
  >
    <div class="flex items-center justify-center ml-32 mt-10">
      <!-- 큰 노란색 원과 텍스트, 버튼 -->
      <div
        class="relative w-[600px] h-[600px] rounded-full bg-kb-yellow-12 flex items-center justify-center"
      >
        <!-- 작은 원과 버튼 -->
        <div
          class="absolute top-[150px] left-[-100px] w-[350px] h-[350px] rounded-full bg-kb-yellow-13 z-10 shadow-md flex items-center justify-center"
        >
          <div
            class="relative w-[300px] h-[300px] rounded-full whitespace-pre-wrap"
          >
            <button
              v-for="(btn, index) in buttons"
              :key="index"
              @click="handleClick(index)"
              @mouseover="hoveredIndex = index"
              @mouseleave="hoveredIndex = null"
              :class="getButtonClass(index)"
              :style="getButtonStyle(index)"
              class="ml-10 mt-12 relative flex items-center"
            >
              {{ btn }}
              <span
                class="block w-3 h-3 rounded-full hover:bg-kb-yellow-1"
                :class="{
                  'bg-kb-yellow-1': focusedIndex === index,
                  'bg-kb-gray-2': focusedIndex !== index,
                }"
              ></span>
            </button>
          </div>
        </div>

        <!-- 텍스트와 버튼 -->
        <div
          class="left-[200px] relative z-10 text-left w-[400px] h-[240px] overflow-hidden"
        >
          <Transition :name="contentTransitionName" mode="out-in">
            <div :key="currentItem.title" class="absolute inset-0">
              <div class="relative inline">
                <h1 class="text-[25px] inline font-bold z-10">
                  {{ currentItem.title }}
                </h1>
                <div
                  class="absolute bottom-0 left-4 bg-kb-yellow-1 w-[93%] h-[50%] opacity-50 inline"
                ></div>
              </div>
              <h3 class="text-4xl font-bold mt-4 text-kb-brown-2">
                {{ currentItem.mainContent }}
              </h3>
              <h3 class="text-[18px] mt-2 whitespace-pre-wrap">
                {{ currentItem.subContent }}
              </h3>
              <div class="mt-8 ml-16">
                <MainPageButton
                  :text="currentItem.buttonText"
                  @click="handleButtonClick"
                />
              </div>
            </div>
          </Transition>
        </div>
      </div>

      <!-- 오른쪽 노트북 이미지 -->
      <div
        class="flex items-center justify-center relative w-[800px] h-[400px] ml-14"
      >
        <div
          class="absolute inset-0 bg-contain bg-no-repeat bg-center"
          style="background-image: url('/images/MainPage/MacBook Air.png')"
        ></div>
        <div class="absolute inset-0 flex items-center justify-center">
          <div class="w-[620px] h-[390px] overflow-hidden">
            <div
              class="relative w-[500px] h-[310px] ml-[58px] mt-8 overflow-hidden"
            >
              <transition :name="transitionName">
                <div
                  :key="currentItem.image"
                  :style="{
                    backgroundImage: `url(${currentItem.image})`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                  }"
                  class="absolute w-full h-full"
                ></div>
              </transition>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import MainPageButton from './MainPageButton.vue';

// data 배열을 이 컴포넌트 안에 직접 포함
const data = ref([
  {
    title: '추천 서비스',
    mainContent: '사용자 맞춤 상품 추천',
    subContent:
      '예금, 적금 부터 보험 대출 그리고 카드까지\n사용자 맞춤형 서비스 제공',
    buttonText: '상품 추천 받으러 가기',
    url: '/products',
    image: '/images/MainPage/categoryPage.png',
  },
  {
    title: '자산 분석 서비스',
    mainContent: '개인 자산 분석 비교 한번에',
    subContent:
      '연령, 소득, 소비 등\n사용자에 맞는 개인 자산 분석 비교 결과 제공',
    buttonText: '자산 분석 하러 가기',
    url: '/asset-detail',
    image: '/images/MainPage/portfolioPage.png',
  },
  {
    title: '자산 설계 서비스',
    mainContent: '개인 자산 맞춤 설계',
    subContent:
      '소비 패턴, 투자 성향 등을 토대로\n개인 자산 맞춤 설계 서비스 제공',
    buttonText: '자산 설계 하러 가기',
    url: '/asset-plan',
    image: '/images/MainPage/simulationPage.png',
  },
]);

// 버튼 목록
const buttons = ref(['맞춤\n상품', '자산\n분석', '자산\n설계']);
const focusedIndex = ref(0);
const hoveredIndex = ref(null); // Hover 상태를 관리하는 변수
const transitionName = ref('slide-right');
const contentTransitionName = ref('fade-up');
const router = useRouter();

// 클릭 시 인덱스를 업데이트하는 함수
const handleClick = (index) => {
  transitionName.value =
    index > focusedIndex.value ? 'slide-left' : 'slide-right';
  contentTransitionName.value =
    index > focusedIndex.value ? 'fade-up' : 'fade-down';
  focusedIndex.value = index;
};

const handleButtonClick = () => {
  router.push(currentItem.value.url);
};

// 현재 선택된 데이터 가져오기
const currentItem = computed(() => data.value[focusedIndex.value]);

const getButtonStyle = (index) => {
  const radius = 120; //작은 원 반지름
  const angle =
    (index * (120 / buttons.value.length) -
      focusedIndex.value * (140 / buttons.value.length)) *
    (Math.PI / 180);
  const x = radius * Math.cos(angle);
  const y = radius * Math.sin(angle);

  return {
    position: 'absolute',
    left: `${x + 100}px`,
    top: `${y + 100}px`,
    transform: 'translate(-50%, -50%)',
    transition: 'all 0.5s ease',
  };
};

// 버튼의 스타일을 설정하는 함수
const getButtonClass = (index) => {
  return focusedIndex.value === index || hoveredIndex.value === index
    ? 'text-kb-yellow-1' // 선택되었거나 hover된 경우
    : 'text-kb-gray-2 hover:text-kb-yellow-1'; // 선택되지 않은 경우 hover 시만 색상 변경
};
</script>

<style scoped>
.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active {
  transition: all 0.7s ease-out;
  position: absolute;
  width: 100%;
  height: 100%;
}

.slide-left-enter-from {
  transform: translateX(100%);
}

.slide-left-leave-to {
  transform: translateX(-100%);
}

.slide-right-enter-from {
  transform: translateX(-100%);
}

.slide-right-leave-to {
  transform: translateX(100%);
}

.fade-up-enter-active,
.fade-up-leave-active,
.fade-down-enter-active,
.fade-down-leave-active {
  transition: all 0.4s ease-out;
  position: absolute;
  width: 100%;
  height: 100%;
}

.fade-up-enter-from,
.fade-down-leave-to {
  opacity: 0;
  transform: translateY(20px);
}

.fade-up-leave-to,
.fade-down-enter-from {
  opacity: 0;
  transform: translateY(-20px);
}

.fade-up-enter-to,
.fade-up-leave-from,
.fade-down-enter-to,
.fade-down-leave-from {
  opacity: 1;
  transform: translateY(0);
}
</style>
