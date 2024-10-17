<template>
  <div>
    <div class="h-[70px] bg-kb-yellow-4"></div>

    <!-- py-16 px-[20%] -->
    <h1 class="font-bold text-[28px] z-20 text-left bg-kb-yellow-4 h-[160px]" :class="`${LAYOUT_VARIANTS.default}`">
      {{ nowProduct }}
    </h1>

    <div>
      <ProductItem
        v-for="(item, index) in displayData(nowData, 0, 5)"
        :key="index"
        :items="item"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :isCard="route.query.request?.includes('card') || nowProduct.includes('카드')"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      />
    </div>

    <div class="flex justify-center my-5">
      <button :disabled="pageIndex <= 0" @click="prevPagingHandler">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1"
          stroke="currentColor"
          class="size-6 text-kb-gray-1"
          :class="pageIndex <= 0 && 'text-kb-gray-3'"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5 8.25 12l7.5-7.5" />
        </svg>
      </button>

      <button
        v-for="(item, index) in pageArray[pageWrapIndex]"
        :key="index"
        @click="pagingHandler(index + pageWrapIndex * 5)"
        class="px-4 py-2 rounded-[10px] text-kb-gray-1"
        :class="pageIndex == index + pageWrapIndex * 5 && 'text-kb-yellow-1 bg-kb-yellow-4'"
      >
        <!-- 버튼 컴포넌트 값 변경을 위함 -->
        {{ index + 1 + pageWrapIndex * 5 }}
      </button>
      <button :disabled="pageIndex >= pageCount - 1" @click="nextPagingHandler">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1"
          stroke="currentColor"
          class="size-6 text-kb-gray-1"
          :class="pageIndex >= pageCount - 1 && 'text-kb-gray-3'"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="m8.25 4.5 7.5 7.5-7.5 7.5" />
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref } from "vue";

import LAYOUT_VARIANTS from "../constant/layout";
import ProductItem from "../components/AllProducts/ProductItem.vue";
import { useRoute } from "vue-router";
import { requestProductsData } from "../apis/api/product";
import { checkCardMapping, creditCardMapping, depositMapping, insuranceMapping, loanMapping } from "../servies/mappingData";
import { getMyFavoritesList } from "../apis/api/favorites";

const nowItemIndex = ref(null);
const dataStartIndex = ref(0);
const dataEndIndex = ref(5);

const pageCount = ref(1);
const pageWrapIndex = ref(0);
const pageIndex = ref(0);
const pageArray = reactive([]);

const displayData = (data, start, end) => {
  return data.slice(start + 5 * pageIndex.value, end + 5 * pageIndex.value);
};

const pagingHandler = index => {
  pageIndex.value = index;
  nowItemIndex.value = null;
};

// 이전 그룹 핸들러 (페이지 그룹을 이전으로 이동)
const prevPagingHandler = () => {
  if (pageIndex.value % 5 === 0 && pageWrapIndex.value > 0) {
    pageWrapIndex.value -= 1; // 그룹을 이전으로 이동
  }
  pageIndex.value -= 1;
  nowItemIndex.value = null;
};

// 다음 그룹 핸들러 (페이지 그룹을 다음으로 이동)
const nextPagingHandler = () => {
  pageIndex.value += 1;
  if (pageIndex.value % 5 === 0 && pageWrapIndex.value < Math.ceil(pageCount.value / 5) - 1) {
    pageWrapIndex.value += 1; // 그룹을 다음으로 이동
  }
  nowItemIndex.value = null;
};

const nowProduct = ref("");
const nowData = ref([]);
const insuranceData = ref([]);
const depositData = ref([]);

onBeforeMount(async () => {
  if (route.query.recommend) {
    // console.log(JSON.parse(route.query.recommend))
    const json = JSON.parse(route.query.recommend);
    if (json.length == 0) return;
    switch (json[0].productId) {
      case "A04":
        nowProduct.value = "체크카드";
        nowData.value = checkCardMapping(json);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "A05":
        nowProduct.value = "신용카드";
        nowData.value = creditCardMapping(json);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "A02":
        nowProduct.value = "대출";
        nowData.value = loanMapping(json);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "A01":
        nowProduct.value = "예/적금";
        nowData.value = depositMapping(json);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "A03":
        nowProduct.value = "보험";
        nowData.value = insuranceMapping(json);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
    }
    pageCount.value = Math.ceil(nowData.value.length / 5);
  } else {
    const res = await requestProductsData(route.query.request);
    // console.log(res)
    switch (route.query.request) {
      case "/check-card":
        nowProduct.value = "체크카드";
        nowData.value = checkCardMapping(res.data);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "/credit-card":
        nowProduct.value = "신용카드";
        nowData.value = creditCardMapping(res.data);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "/loan":
        nowProduct.value = "대출";
        nowData.value = loanMapping(res.data);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "/deposit":
        nowProduct.value = "예/적금";
        nowData.value = depositMapping(res.data);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      case "/insurance":
        nowProduct.value = "보험";
        nowData.value = insuranceMapping(res.data);
        pageCount.value = Math.ceil(nowData.value.length / 5);
        break;
      default:
        break;
    }
  }
  for (let i = 0; i < pageCount.value; i += 5) {
    if (i + 5 <= pageCount.value) {
      pageArray.push(new Array(5).fill(null));
    } else {
      pageArray.push(new Array(Math.ceil(pageCount.value % 5)).fill(null));
    }
  }

  // 받은 데이터들 찜 기능추가
  const favoritesList = await getMyFavoritesList();

  for (let favorites of favoritesList.data) {
    const id = favorites.productNum;
    const productId = favorites.productId;

    for (let data of nowData.value) {
      if (data.id === id && data.productId === productId) {
        data.isFavorite = true;
      }
    }
  }
});

const route = useRoute();
</script>
