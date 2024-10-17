<template>
  <div class="flex flex-col relative">
    <h1 class="font-bold text-[28px] md:max-laptop:mb-10 mb-24 z-20 text-left">
      {{ `${item.title} TOP3` }}
    </h1>
    <div class="w-full">
      <div class="flex flex-col items-center justify-center w-full h-auto">
        <!-- <ProductsCards v-for="(item, index) in cardData" :item="item" :key="index" :class="[`transition-transform order-${cardOrder[currentIndex][index]}`, cardOrder[currentIndex][index]==2 ? `scale-110` : '']" /> -->
        <div
          class="flex flex-row h-[350px] items-center w-[900px] overflow-hidden"
          id="carousel-container"
        >
          <div
            v-for="(items, index) in mappingData(data[item.title])"
            :key="index"
            class="text-font-color px-4 flex-none basis-[300px] flex items-center justify-center z-10"
            :class="[
              index == currentIndex && 'z-20',
              isAnimate && 'transition-all',
            ]"
            :style="{
              transform: `translateX(-${(currentIndex - 1) * 300}px) scale(${
                currentIndex === index ? 1.1 : 1
              })`,
            }"
          >
            <ProductsCards
              :items="items"
              :isCard="item.title.includes('카드')"
              :isHover="isHover"
              :isNow="currentIndex == index"
              :currentIndex="currentIndex"
              :index="index"
              @click="carouselItemClickHandler(index)"
              @mouseover="productMouseOverHandler(index)"
              @mouseleave="productMouseLeaveHandler"
            />
          </div>
        </div>

        <div class="z-10 flex gap-3 mt-7">
          <button
            v-for="(item, index) in data[item.title]"
            :key="index"
            :class="[
              'w-3 h-3 rounded-full',
              index + 2 == currentIndex ? 'bg-kb-yellow-1' : 'bg-kb-gray-2',
            ]"
            @click="currentIndex = index + 2"
          ></button>
        </div>

        <!-- 동그라미 효과 -->
        <div
          class="absolute w-[589px] h-[589px] md:max-laptop:w-[489px] md:max-laptop:h-[489px] border border-kb-yellow-3 rounded-full flex justify-center items-center transition-all"
          :class="isHover ? 'scale-[2.5] z-10' : 'z-0'"
        >
          <div
            class="w-[496px] h-[496px] md:max-laptop:w-[389px] md:max-laptop:h-[389px] bg-kb-yellow-3 absolute rounded-full blur-sm"
          ></div>
        </div>
      </div>
    </div>

    <!-- 전체 조회 버튼 -->
    <div
      class="absolute bottom-[-150px] left-1/2 transform -translate-x-1/2 z-0"
    >
      <button
        class="px-4 py-2.5 rounded-lg text-gray-500 bg-white shadow-md text-[16px] hover:bg-gray-100 hover:text-font-color duration-300"
        @click="
          () => {
            router.push({
              path: '/all-products',
              query: { request: item.request },
            });
          }
        "
      >
        {{ `${item.title} 전체 조회` }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

import ProductsCards from './ProductsAllView/ProductsCards.vue';
import LAYOUT_VARIANTS from '../../constant/layout';

const router = useRouter();

const props = defineProps({
  item: {
    type: Object,
    required: true,
  },
});

const isAnimate = ref(true);
const isHover = ref(false);
const currentIndex = ref(2);
const isClickable = ref(true); // 클릭 가능 여부

const cardOrder = [
  [3, 1, 2], // 1일때
  [1, 2, 3], // 2일때
  [2, 3, 1], // 3일때
];

const data = ref({
  체크카드: [
    {
      cardImageUrl: '/images/Products/Top3Products/check-card/1.png',
      cardName: '첵첵 체크카드(마루는강쥐)',
      cardContent: '마루와 함께 혜택도 첵첵!',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=01760',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/check-card/2.png',
      cardName: '트래블러스 체크카드\n(토심이)',
      cardContent: '여행이 일상인 프로여행러라면',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=09564',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/check-card/3.png',
      cardName: '총무 체크카드(홀로)',
      cardContent: '모임의 밥과 후식 할인 담당',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=07980',
    },
  ],
  신용카드: [
    {
      cardImageUrl: '/images/Products/Top3Products/credit-card/1.png',
      cardName: 'WE:SH Travel',
      cardContent: '외화 충전·환전 없이\n우대 환율로 결제되는',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=09561',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/credit-card/2.png',
      cardName: 'Easy pick카드',
      cardContent: '주요 생활 영역부터 내가 Pick한\n영역까지 모두 적립 Easy!',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=09243',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/credit-card/3.png',
      cardName: '캐시노트 KB 국민카드',
      cardContent: '캐시노트 월정액과\n사업지원 할인을 동시에',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=04291',
    },
  ],
  예금적금: [
    {
      cardImageUrl: '/images/Products/Top3Products/deposit/1.png',
      cardName: 'KB 내맘대로 적금',
      cardContent: '누구나 쉽게 우대받는 DIY',
      pageUrl:
        'https://obank.kbstar.com/quics?page=C016613&cc=b061496:b061645&isNew=N&prcode=DP01000821',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/deposit/2.png',
      cardName: 'KB Star 정기예금',
      cardContent: 'Digital KB의 대표 정기예금',
      pageUrl:
        'https://obank.kbstar.com/quics?page=C016613&cc=b061496:b061645&isNew=N&prcode=DP01000938',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/deposit/3.png',
      cardName: '직장인우대적금',
      cardContent: '직장인의 알짜배기 재테크',
      pageUrl:
        'https://obank.kbstar.com/quics?page=C016613&cc=b061496:b061645&isNew=N&prcode=DP01000228',
    },
  ],
  대출: [
    {
      cardImageUrl: '/images/Products/Top3Products/loan/1.png',
      cardName: 'KB 내맘대로 적금',
      cardContent: '누구나 쉽게 우대받는 DIY',
      pageUrl:
        'https://obank.kbstar.com/quics?page=C016613&cc=b061496:b061645&isNew=N&prcode=DP01000821',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/loan/2.png',
      cardName: 'Easy pick카드',
      cardContent: '주요 생활 영역부터 내가 Pick한 영역까지 모두 적립 Easy!',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=09243',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/loan/3.png',
      cardName: '캐시노트 KB 국민카드',
      cardContent: '캐시노트 월정액과 사업지원 할인을 동시에',
      pageUrl:
        'https://card.kbcard.com/CRD/DVIEW/HCAMCXPRICAC0076?mainCC=a&cooperationcode=04291',
    },
  ],
  보험: [
    {
      cardImageUrl: '/images/Products/Top3Products/insurance/1.png',
      cardName: 'KB Yes!365 건강보험(연만기)',
      cardContent: '자녀/건강보험',
      pageUrl: 'https://www.kbinsure.co.kr/CG302230001.ec',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/insurance/2.png',
      cardName: 'KB자동차보험',
      cardContent: '자동차/운전자',
      pageUrl: 'https://www.kbinsure.co.kr/CG301010012.ec',
    },
    {
      cardImageUrl: '/images/Products/Top3Products/insurance/3.png',
      cardName: 'KB 생활비지급암보험 무배당(갱신형)',
      cardContent: '암보험',
      pageUrl:
        'https://www.kblife.co.kr/insurance-product/productDetails.do?id=501552401&productType=4&linkCd=OF_PD_PC_01',
    },
  ],
});

const productMouseOverHandler = (index) => {
  if (index == currentIndex.value) {
    isHover.value = true;
  }
};

const productMouseLeaveHandler = () => {
  isHover.value = false;
};

const mappingData = (param) => {
  const cardStart = param.slice(0, 2);
  const cardEnd = param.slice(-2);
  return [...cardEnd, ...param, ...cardStart];
};

watch(currentIndex, () => {
  if (currentIndex.value == 5) {
    setTimeout(() => {
      isAnimate.value = false;
      currentIndex.value = 2;
    }, 150);
  } else if (currentIndex.value == 1) {
    setTimeout(() => {
      isAnimate.value = false;
      currentIndex.value = 4;
    }, 150);
  }

  setTimeout(() => {
    isAnimate.value = true;
  }, 200);
});

const carouselItemClickHandler = (index) => {
  // console.log(index);
  // 캐러셀 넘어갈때 클릭 빠르게 하면 item 벗어나는 거 막음
  if (!isClickable.value) return; // 클릭이 막혀 있으면 이벤트를 무시

  currentIndex.value = index;
  isClickable.value = false; // 클릭 이벤트 막기

  setTimeout(() => {
    isClickable.value = true; // 150ms 후 다시 클릭 가능
  }, 200);
};
</script>
