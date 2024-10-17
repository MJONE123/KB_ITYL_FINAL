<template>
  <div class="relative h-screen">
    <div class="flex items-center justify-center w-full h-full bg-kb-yellow-4">
      <div class="h-[400px] bg-kb-yellow-8 w-full flex items-center px-[22%]">
        <!-- 3원 -->
        <div class="relative flex-1 h-full mt-6 mr-9">
          <StrategyCircle
            v-for="(title, index) in strategyTitle"
            :key="index"
            :class="currentIndex === index ? 'opacity-100' : 'opacity-0'"
            class="absolute animation top-[7%] -left-20"
            :title="title"
            :data="strategyData[title]"
          />
        </div>

        <!-- contents -->
        <div class="relative flex-[2] h-full">
          <StrategyText
            :percent="spendingPercent"
            :isDecline="isDecline"
            :strategy="strategy"
            :class="currentIndex === 0 ? 'opacity-100' : 'opacity-0'"
            class="absolute animation top-[22%] -left-16"
          />
          <StrategyRecommend
            :class="currentIndex === 1 ? 'opacity-100' : 'opacity-0'"
            class="absolute top-[26%] animation"
            :savingStrategy="savingStrategy"
          />
          <StrategyRecommend
            :class="currentIndex === 2 ? 'opacity-100' : 'opacity-0'"
            class="absolute top-[26%] animation"
            :investmentStrategy="investmentStrategy"
          />
        </div>
      </div>

      <!-- 이동 버튼 -->
      <StrategyButton
        :currentIndex="currentIndex"
        @prev="prevSlide"
        @next="nextSlide"
      />
    </div>

    <!-- 부채 관리 솔루션 -->
    <div
      ref="solutionSection"
      class="relative flex items-center justify-center w-screen h-screen bg-kb-yellow-4"
    >
      <div class="relative w-full h-full">
        <!-- SVG for lines -->
        <svg
          class="absolute top-0 left-0 z-0 w-full h-full"
          :class="{ 'lines-visible': isVisible }"
        >
          <line
            v-for="(_, index) in solutionTitle"
            :key="`line-${index}`"
            :class="[`line-${getSolutionPosition(index)}`, 'solution-line']"
            x1="50%"
            y1="50%"
            :x2="getLineEndX(index)"
            :y2="getLineEndY(index)"
            :style="{ animationDelay: `${index * 0.5}s` }"
          />
        </svg>

        <!-- 3원 -->
        <div
          ref="centerCircle"
          class="absolute z-10 transform -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
        >
          <StrategyCircle :title="'부채관리'" :text="'솔루션'" />
        </div>

        <!-- 솔루션들 -->
        <StrategyDeptSolution
          v-for="(title, index) in solutionTitle"
          :key="index"
          :title="title"
          :content="solutionContent[title]"
          :class="[getSolutionPosition(index), 'absolute']"
          :positionClass="getSolutionPosition(index)"
          :isVisible="isVisible && visibleSolutions[index]"
          :animationDelay="`${index * 0.5 + 0.5}s`"
        />
      </div>
      <div class="absolute text-center bottom-16">
        <CommonButton @click="goToAssetPlan" :text="'돌아가기'" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../../stores/auth';

import StrategyCircle from './StrategyForPrivate/StrategyCircle.vue';
import StrategyText from './StrategyForPrivate/StrategyText.vue';
import StrategyRecommend from './StrategyForPrivate/StrategyRecommend.vue';
import StrategyDeptSolution from './StrategyForPrivate/StrategyDeptSolution.vue';
import StrategyButton from './StrategyButton.vue';

import CommonButton from '../common/CommonButton.vue';
import { loadingStateStore } from '../../stores/loadingStateStore';

// 개선 전략 데이터
const strategyTitle = ref([]);
const strategyData = ref({});
const spendingPercent = ref(0);
const strategy = ref('');
const isDecline = ref(false);
const savingStrategy = ref({});
const investmentStrategy = ref({});
const solutionTitle = ref([]);
const solutionContent = ref({});

const loadingStore = loadingStateStore();

// axios 연결
const fetchStrategy = async () => {
  const authStore = useAuthStore();
  const token = authStore.token;

  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }

  try {
    loadingStore.setIsAssetPlanLoadingState(true);
    const response = await axios.post(
      `http://localhost:8080/assets/plan`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }
    );

    if(response.status==200){
      loadingStore.setIsAssetPlanLoadingState(false);
    }
    const data = response?.data?.jsonNode;

    strategyTitle.value = Object.keys(data.개선된_전략_요약);
    strategyData.value = data.개선된_전략_요약;
    spendingPercent.value = data.개선된_전략_요약.지출_조정.월_지출;
    isDecline.value = data.개선된_전략_요약.지출_조정.감소여부;
    strategy.value = data.개선된_전략_요약.지출_조정.방안;
    savingStrategy.value = data.개선된_전략_요약.저축_전략;
    investmentStrategy.value = data.개선된_전략_요약.투자_전략;
    solutionTitle.value = Object.keys(data.부채_관리);
    solutionContent.value = data.부채_관리;
  } catch (error) {
    console.error('데이터 가져오기 실패', error);
  }
};

onMounted(() => {
  fetchStrategy();
});

const solutionSection = ref(null);
const isVisible = ref(false);
const visibleSolutions = ref([]);
const animationKey = ref(0);

const getSolutionPosition = (index) => {
  const positions = [
    'solution-top-left', // 왼쪽 위
    'solution-top-right', // 오른쪽 위
    'solution-bottom-left', // 왼쪽 아래
    'solution-bottom-right', // 오른쪽 아래
  ];
  return positions[index];
};

const currentIndex = ref(0);

const nextSlide = () => {
  currentIndex.value = currentIndex.value + 1;
};

const prevSlide = () => {
  currentIndex.value = currentIndex.value - 1;
};

const getLineEndX = (index) => {
  const position = getSolutionPosition(index);
  return position.includes('left') ? '25%' : '75%';
};

const getLineEndY = (index) => {
  const position = getSolutionPosition(index);
  return position.includes('top') ? '30%' : '70%';
};

const router = useRouter();

const goToAssetPlan = () => {
  router.push('/asset-plan');
};

// solutionTitle이 변경될 때마다 visibleSolutions를 업데이트
watch(
  solutionTitle,
  (newValue) => {
    visibleSolutions.value = newValue.map(() => false);
  },
  { immediate: true }
);

watch(isVisible, (newValue) => {
  if (newValue) {
    animationKey.value++;
    solutionTitle.value.forEach((_, index) => {
      setTimeout(() => {
        visibleSolutions.value = [
          ...visibleSolutions.value.slice(0, index),
          true,
          ...visibleSolutions.value.slice(index + 1),
        ];
      }, (index * 0.5 + 1) * 1000);
    });
  } else {
    visibleSolutions.value = solutionTitle.value.map(() => false);
  }
});

onMounted(() => {
  const observer = new IntersectionObserver(
    ([entry]) => {
      isVisible.value = entry.isIntersecting;
    },
    { threshold: 0.5 }
  );

  if (solutionSection.value) {
    observer.observe(solutionSection.value);
  }

  onUnmounted(() => {
    if (solutionSection.value) {
      observer.unobserve(solutionSection.value);
    }
  });
});
</script>

<style scoped>
.animation {
  transition: opacity 0.5s, transform 0.5s; /* 부드러운 전환 */
}

.solution-line {
  stroke: #f6ce46;
  stroke-width: 1;
  stroke-dasharray: 800;
  stroke-dashoffset: 800;
  animation: none;
}

.lines-visible .solution-line {
  animation: drawLine 1s ease-out forwards;
}

@keyframes drawLine {
  to {
    stroke-dashoffset: 0;
  }
}

@keyframes fadeInScale {
  to {
    opacity: 1;
    transform: scale(1);
  }
}

.solution-top-left {
  top: 19%;
  left: 17%;
}

.solution-top-right {
  top: 19%;
  right: 17%;
}

.solution-bottom-left {
  bottom: 19%;
  left: 17%;
}

.solution-bottom-right {
  bottom: 19%;
  right: 17%;
}
</style>
