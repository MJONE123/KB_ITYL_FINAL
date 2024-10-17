<template>
  <div class="h-[calc(100vh-70px)]">
    <div class="h-[70px]"></div>

    <div
      class="w-[500px] h-[500px] fixed top-[25%] left-[35%] bg-kb-yellow-4 opacity-50 rounded-full blur-3xl -z-10"
    ></div>

    <!-- 제목 섹션 -->
    <div class="flex justify-center h-[14%] my-10">
      <h1 class="text-[32px]">1년 후 예상 자산</h1>
    </div>

    <!-- 차트와 텍스트 섹션 -->
    <div class="px-[18%] h-[100vh] w-full">
      <!-- 시뮬레이션 데이터 텍스트 -->
      <div class="flex flex-wrap justify-between gap-8">
        <template v-for="(item, index) in simulationData" :key="index">
          <!-- 텍스트 정보 -->
          <div class="flex flex-col items-center w-[45%] justify-center mb-6">
            <!-- 차트 렌더링 -->
            <div
              class="w-[100%] h-full p-4 mb-4"
              :style="{ backgroundColor: item.bgColor }"
            >
              <Line :data="getChartData(item.title)" :options="chartOptions" />
            </div>

            <div class="flex items-center w-full gap-8">
              <div
                class="rounded-full border-2 inline-flex flex-col w-[130px] h-[130px] items-center justify-center"
                :style="{ borderColor: item.hexColor }"
              >
                <span>{{ item.title }}</span>
                <img
                  :src="`/images/AssetPlanSimulation/${
                    item.title.includes('자산')
                      ? `asset-${item.color}`
                      : `dept-${item.color}`
                  }.png`"
                  alt=""
                  class="w-[50px] h-[50px] object-contain"
                />
              </div>

              <div class="flex flex-col">
                <span>현재 전략: {{ item.data.현재_전략 }}</span>
                <span>개선된 전략: {{ item.data.개선된_전략 }}</span>
                <div class="h-[1px] bg-black"></div>
                <span class="text-[20px] font-semibold">{{
                  `${amountData[index].toLocaleString()}원 ${
                    item.data['상승 여부'] ? '더 상승' : '더 감소'
                  }`
                }}</span>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>

    <!-- 종합분석 -->
    <div>
      <div
        class="flex w-full h-auto mt-[120px] justify-center items-center flex-wrap px-[40%] gap-4"
      >
        <div v-for="(item, index) in ratingData" :key="index">
          <p class="text-[40px]">{{ item }}</p>
        </div>
      </div>

      <div class="h-[200px] mt-16 text-center">
        <CommonButton @click="goToAssetPlan" :text="'돌아가기'" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { Line } from 'vue-chartjs';
import { useRouter } from 'vue-router';
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
} from 'chart.js';

import CommonButton from '../components/common/CommonButton.vue';
import { onBeforeMount, ref } from 'vue';
import { getAssetAfterYear } from '../apis/api/assetPlan';
import { loadingStateStore } from '../stores/loadingStateStore';

// ChartJS 플러그인 등록
ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement
);

// 종합 분석 데이터
const ratingData = ref([]);

// 시뮬레이션 데이터
const simulationData = ref([]);

// 차트 데이터
const chartData = ref({});

// 차이 데이터
const amountData = ref([]);

// 차트 옵션
const chartOptions = {
  // responsive: true,
  plugins: {
    legend: {
      display: true,
      position: 'top',
    },
  },
  scales: {
    y: {
      beginAtZero: false,
      ticks: {
        // y축 눈금을 5개로 고정
        maxTicksLimit: 5,
        // 만 단위로 포맷팅
        callback: function (value) {
          return (value / 10000).toLocaleString() + '만';
        },
      },
    },
  },
};

// 각 항목에 맞는 차트 데이터 반환
const getChartData = (title) => {
  if (title === '총 자산') {
    return {
      labels: chartData.value.현재_전략.labels,
      datasets: [
        {
          ...chartData.value.현재_전략.datasets[0],
          borderColor: '#D9D9D9',
          backgroundColor: '#D9D9D9',
        },
        {
          ...chartData.value.개선된_전략.datasets[0],
          borderColor: '#F4BF42',
          backgroundColor: '#F4BF42',
        },
      ],
    };
  } else if (title === '총 부채') {
    return {
      labels: chartData.value.현재_전략.labels,
      datasets: [
        {
          ...chartData.value.현재_전략.datasets[1],
          borderColor: '#D9D9D9',
          backgroundColor: '#D9D9D9',
        },
        {
          ...chartData.value.개선된_전략.datasets[1],
          borderColor: '#FF5252',
          backgroundColor: '#FF5252',
        },
      ],
    };
  } else if (title === '순 자산') {
    return {
      labels: chartData.value.현재_전략.labels,
      datasets: [
        {
          ...chartData.value.현재_전략.datasets[2],
          borderColor: '#D9D9D9',
          backgroundColor: '#D9D9D9',
        },
        {
          ...chartData.value.개선된_전략.datasets[2],
          borderColor: '#58AEFF',
          backgroundColor: '#58AEFF',
        },
      ],
    };
  } else if (title === '총 투자금액') {
    return {
      labels: chartData.value.현재_전략.labels,
      datasets: [
        {
          ...chartData.value.현재_전략.datasets[3],
          borderColor: '#D9D9D9',
          backgroundColor: '#D9D9D9',
        },
        {
          ...chartData.value.개선된_전략.datasets[3],
          borderColor: '#B1EF5A',
          backgroundColor: '#B1EF5A',
        },
      ],
    };
  }
};

const router = useRouter();

const goToAssetPlan = () => {
  router.push('/asset-plan');
};

const loadingStore = loadingStateStore();

onBeforeMount(async () => {
  loadingStore.setIsAssetPlanLoadingState(true);

  console.log(loadingStore.isAssetPlanLoading);

  const afterYearData = await getAssetAfterYear();
  // console.log(afterYearData);

  if (afterYearData.status == 200) {
    loadingStore.setIsAssetPlanLoadingState(false);

    const temp = afterYearData.data['전략_비교_분석'];
    chartData.value = afterYearData.data['그래프_데이터'];
    ratingData.value = temp['종합_평가(#키워드)'].split(' ');
    simulationData.value = [
      {
        title: '총 자산',
        data: { ...temp['총_자산_변화'] },
        color: 'yellow',
        hexColor: '#F4BF42',
        bgColor: '#FFFBE7',
      },
      {
        title: '총 부채',
        data: { ...temp['부채_변화'] },
        color: 'pink',
        hexColor: '#FFAAAA',
        bgColor: '#FFF6FA',
      },
      {
        title: '순 자산',
        data: { ...temp['순_자산_변화'] },
        color: 'blue',
        hexColor: '#8AC6FF',
        bgColor: '#EEFCFF',
      },
      {
        title: '총 투자금액',
        data: { ...temp['투자_성과'] },
        color: 'green',
        hexColor: '#B1EF5A',
        bgColor: '#F0FFEE',
      },
    ];
    // console.log(chartData.value);
    // console.log(ratingData.value);
    // console.log(simulationData.value);
    const nowAmount = [];
    // console.log(chartData.value)
    // console.log(chartData.value.개선된_전략.datasets)
    for (let c of chartData.value.개선된_전략.datasets) {
      nowAmount.push(c.data[4]);
    }
    const planAmount = [];
    for (let c of chartData.value.현재_전략.datasets) {
      planAmount.push(c.data[4]);
    }
    for (let i = 0; i <= 4; i++) {
      amountData.value.push(nowAmount[i] - planAmount[i]);
    }
  }
});
</script>

<style scoped>
.flex-2 {
  flex: 4 1 0;
}
</style>
