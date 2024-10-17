<template>
  <div class="flex flex-col items-center w-full">
    <p class="text-[32px] text-font-color font-semibold">
      자산 및 부채 분석 결과
    </p>
    <div class="bg-gray-600 h-0.5 w-[280px]"></div>
  </div>

  <p class="mt-3 flex justify-center text-[18px] text-kb-gray-2">
    자세한 결과를 보고 싶다면 분석 결과를 클릭하여 확인하세요
  </p>

  <div class="flex justify-center mt-[100px] space-x-10">
    <!-- 첫 번째 카드 (총 자산) -->
    <div
      class="group perspective w-[450px] h-[450px] relative cursor-pointer"
      @click="toggleFlip(0)"
    >
      <div
        class="relative w-full h-full transition-transform duration-500 transform-style-3d"
        :class="flipped[0] ? 'rotateY-180' : ''"
      >
        <!-- 앞면 -->
        <div
          class="absolute w-full h-full backface-hidden bg-kb-blue-6 rounded-[50px] flex flex-col p-10"
        >
          <button
            class="p-2 w-[200px] h-10 rounded-[30px] bg-kb-blue-5 text-[20px] text-white mb-6 font-light"
          >
            총 자산
          </button>
          <p class="text-[20px] text-kb-brown-1">20대 평균 총 자산의</p>
          <p class="text-[36px] font-bold text-font-color mb-2">
            약 {{ compareTotal }}배 입니다.
          </p>
          <AssetGraph
            :userTotalAssets="userTotalAssets"
            :averageTotalAssets="averageTotalAssets"
          />
        </div>

        <!-- 뒷면 -->
        <div
          class="absolute w-full h-full rounded-[50px] flex flex-col items-center justify-start transform rotateY-180 backface-hidden shadow-lg"
        >
          <div
            class="w-full h-[200px] bg-kb-blue-6 flex flex-col rounded-t-[50px] p-10"
          >
            <p class="text-[24px] text-font-color mb-6 font-light">총 자산</p>
            <p class="text-[28px] font-bold text-font-color">
              {{ assetKeywords[0] }}
            </p>
            <p class="text-[28px] font-bold text-font-color">
              {{ assetKeywords[1] }}
            </p>
          </div>
          <img
            :src="assetImage1"
            alt="icon"
            class="absolute top-[140px] ml-[250px] z-20 w-[110px] h-[110px]"
          />
          <div
            class="w-full h-full bg-white rounded-b-[50px] flex px-10 items-center"
          >
            <p
              class="text-[20px] text-font-color font-thin"
              v-html="assetAnalysis"
            ></p>
          </div>
        </div>
      </div>
    </div>

    <!-- 두 번째 카드 (자산 대비 부채율) -->
    <div
      class="group perspective w-[450px] h-[450px] relative cursor-pointer"
      @click="toggleFlip(1)"
    >
      <div
        class="relative w-full h-full transition-transform duration-500 transform-style-3d"
        :class="flipped[1] ? 'rotateY-180' : ''"
      >
        <!-- 앞면 -->
        <div
          class="absolute w-full h-full backface-hidden bg-kb-yellow-8 rounded-[50px] flex flex-col p-10"
        >
          <button
            class="p-2 w-[200px] h-10 rounded-[30px] bg-kb-brown-5 text-[20px] text-white mb-6 font-light"
          >
            자산 대비 부채 비율
          </button>
          <p class="text-[20px] text-kb-brown-1">
            20대 평균 자산 대비 부채 비율의
          </p>
          <p class="text-[36px] font-bold text-font-color mb-2">
            약 {{ compareAssetDebt }}% 입니다.
          </p>
          <DebtGraph
            :assetDebtRatio="assetDebtRatio"
            :averageDebtRatio="averageDebtRatio"
          />
        </div>

        <!-- 뒷면 -->
        <div
          class="absolute w-full h-full rounded-[50px] flex flex-col items-center justify-start transform rotateY-180 backface-hidden shadow-lg"
        >
          <div
            class="w-full h-[200px] bg-kb-yellow-8 flex flex-col rounded-t-[50px] p-10"
          >
            <p class="text-[24px] text-font-color mb-6 font-light">
              자산 대비 부채 비율
            </p>
            <p class="text-[28px] font-bold text-font-color">
              {{ assetDebtKeywords[0] }}
            </p>
            <p class="text-[28px] font-bold text-font-color">
              {{ assetDebtKeywords[1] }}
            </p>
          </div>
          <img
            :src="assetImage2"
            alt="icon"
            class="absolute top-[120px] ml-[250px] z-20 w-[130px] h-[130px]"
          />
          <div
            class="w-full h-full bg-white rounded-b-[50px] flex px-10 items-center"
          >
            <p class="text-[20px] text-font-color font-thin">
              {{ assetDebtAnalysis }}
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- 세 번째 카드 (연 소득 대비 부채 비율) -->
    <div
      class="group perspective w-[450px] h-[450px] relative cursor-pointer"
      @click="toggleFlip(2)"
    >
      <div
        class="relative w-full h-full transition-transform duration-500 transform-style-3d"
        :class="flipped[2] ? 'rotateY-180' : ''"
      >
        <!-- 앞면 -->
        <div
          class="absolute w-full h-full backface-hidden bg-kb-pink-5 rounded-[50px] flex flex-col p-10"
        >
          <button
            class="p-2 w-[200px] h-10 rounded-[30px] bg-kb-pink-6 text-[20px] text-white mb-6 font-light"
          >
            연 소득 대비 부채 비율
          </button>
          <p class="text-[20px] text-kb-brown-1">
            20대 평균 연 소득 대비 부채 비율의
          </p>
          <p class="text-[36px] font-bold text-font-color mb-2">
            약 {{ compareIncomeDebt }}% 입니다.
          </p>
          <IncomeGraph
            :incomeDebtRatio="incomeDebtRatio"
            :averageIncomeRatio="averageIncomeRatio"
          />
        </div>

        <!-- 뒷면 -->
        <div
          class="absolute w-full h-full rounded-[50px] flex flex-col items-center justify-start transform rotateY-180 backface-hidden shadow-lg"
        >
          <div
            class="w-full h-[200px] bg-kb-pink-5 flex flex-col rounded-t-[50px] p-10"
          >
            <p class="text-[24px] text-font-color mb-6 font-light">
              연 소득 대비 부채 비율
            </p>
            <p class="text-[28px] font-bold text-font-color">
              {{ incomeDebtKeywords[0] }}
            </p>
            <p class="text-[28px] font-bold text-font-color">
              {{ incomeDebtKeywords[1] }}
            </p>
          </div>
          <img
            :src="assetImage3"
            alt="icon"
            class="absolute top-[130px] ml-[250px] z-20 w-[120px] h-[120px]"
          />
          <div
            class="w-full h-full bg-white rounded-b-[50px] flex px-10 items-center"
          >
            <p class="text-[20px] text-font-color font-thin">
              {{ incomeDebtAnalysis }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onBeforeMount } from 'vue';
import { useAuthStore } from '../../stores/auth';

import AssetGraph from './graph/AssetGraph.vue';
import DebtGraph from './graph/DebtGraph.vue';
import IncomeGraph from './graph/IncomeGraph.vue';
import { loadingStateStore } from '../../stores/loadingStateStore';

// 데이터 상태 관리
const flipped = ref([false, false, false]);

//연령대
const age = ref('');

// 비교 데이터 변수들
const compareTotal = ref(0);
const compareAssetDebt = ref(0);
const compareIncomeDebt = ref(0);

// 첫 번째 값 (총 자산)
const userTotalAssets = ref(0);
const averageTotalAssets = ref(0);
const assetAnalysis = ref('');

// 두 번째 값 (자산 대비 부채율)
const assetDebtRatio = ref(0);
const averageDebtRatio = ref(0);
const assetDebtAnalysis = ref('');

// 세 번째 값 (연 소득 대비 부채율)
const incomeDebtRatio = ref(0);
const averageIncomeRatio = ref(0);
const incomeDebtAnalysis = ref('');

// 키워드들
const assetKeywords = ref([]); // 자산 비교 해시태그
const assetDebtKeywords = ref([]); // 자산 대비 부채 비율 해시태그
const incomeDebtKeywords = ref([]); // 연 소득 대비 부채 비율 해시태그

const loadingStore = loadingStateStore();

// axios 연결
const fetchAssetLoanData = async () => {
  const authStore = useAuthStore();
  const token = authStore.token;
  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }

  loadingStore.setIsAssetAnalyzeLoading(true, 1);

  try {
    const response = await axios.post(
      `http://localhost:8080/assets/loan`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }
    );

    if (response.status == 200) {
      loadingStore.setIsAssetAnalyzeLoading(false, 1);
    }

    const data = response?.data?.jsonNode?.['같은 연령대 평균과 비교'];
    if (!data) {
      throw new Error('유효한 데이터를 가져오지 못했습니다.');
    }

    console.log(data);

    userTotalAssets.value = parseInt(data['자산 비교']['사용자 자산']);
    averageTotalAssets.value = parseFloat(
      data['자산 비교']['같은 연령대 평균 자산(뒤 0000붙여서)']
    );
    compareTotal.value = parseFloat(
      data['자산 비교']['사용자와 같은연령대 비율 차이(n배)']
    ).toFixed(2);
    assetAnalysis.value = data['자산 비교']['연령 별 자산 총평'].replace(
      '|',
      '<br>'
    );
    assetKeywords.value = data['자산 비교']['#요약키워드(2개)'];

    // 자산 대비 부채 비율 비교
    assetDebtRatio.value = parseFloat(
      data['자산 대비 부채 비율 비교']['사용자 자산 대비 부채 비율(% 제외)']
    );
    averageDebtRatio.value = parseFloat(
      data['자산 대비 부채 비율 비교'][
        '같은 연령대 평균 자산 대비 부채 비율(% 제외,소숫점 두자리수까지)'
      ]
    );
    compareAssetDebt.value = Math.abs(
      parseFloat(
        data['자산 대비 부채 비율 비교']['사용자와 같은연령대 비율 차이(n배)']
      )
    ).toFixed(2);
    assetDebtAnalysis.value =
      data['자산 대비 부채 비율 비교']['연령 별 자산대비 부채 총평'];
    assetDebtKeywords.value =
      data['자산 대비 부채 비율 비교']['#요약키워드(2개)'];

    // 연 소득 대비 부채 비율 비교
    incomeDebtRatio.value = parseFloat(
      data['연 소득 대비 부채 비율 비교'][
        '사용자 연 소득 대비 부채 비율(%제외)'
      ]
    );
    averageIncomeRatio.value = parseFloat(
      data['연 소득 대비 부채 비율 비교'][
        '같은 연령대 평균 연 소득 대비 부채 비율(% 제외,소숫점 두자리수까지)'
      ]
    );
    compareIncomeDebt.value = Math.abs(
      parseFloat(
        data['연 소득 대비 부채 비율 비교'][
          '사용자와 같은연령대 비율 차이(n배)'
        ]
      )
    ).toFixed(2);
    incomeDebtAnalysis.value =
      data['연 소득 대비 부채 비율 비교']['연령 별 소득 대비 부채 총평'];
    incomeDebtKeywords.value =
      data['연 소득 대비 부채 비율 비교']['# 요약키워드(2개)'];
  } catch (error) {
    console.error('데이터 오류', error);
  }
};

onBeforeMount(fetchAssetLoanData); // 데이터 가져오기

// 이미지 경로
const assetImage1 = '/images/AssetAnalyze/asset1.png';
const assetImage2 = '/images/AssetAnalyze/asset2.png';
const assetImage3 = '/images/AssetAnalyze/asset3.png';

// 카드 회전 함수
const toggleFlip = (index) => {
  flipped.value[index] = !flipped.value[index];
};
</script>

<style scoped>
.perspective {
  perspective: 1000px;
}

.transform-style-3d {
  transform-style: preserve-3d;
}

.backface-hidden {
  backface-visibility: hidden;
}

.rotateY-180 {
  transform: rotateY(180deg);
}
</style>
