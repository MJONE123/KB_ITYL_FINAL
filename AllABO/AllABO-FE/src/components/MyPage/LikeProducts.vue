<template>
  <!-- 찜한 상품 섹션 -->
  <div class="mt-[75px] flex justify-between items-center">
    <p class="text-[20px] text-font-color px-8">찜한 상품</p>
    <div class="relative ml-4">
      <select v-model="selectedCategory" class="p-2 border border-gray-300 rounded-md text-kb-gray-1" @change="selectHandler">
        <option value="category0" class="py-2 text-kb-gray-1">전체</option>
        <option value="category1" class="text-kb-gray-1">카드</option>
        <option value="category2" class="text-kb-gray-1">예/적금</option>
        <option value="category3" class="text-kb-gray-1">보험</option>
        <option value="category4" class="text-kb-gray-1">대출</option>
      </select>
    </div>
  </div>

  <div class="w-full mt-4 border-t-2">
    <div v-if="selectedCategory === 'category0'" class="mt-10">
      <ProductItem
        v-for="(item, index) in favoriteData"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :items="item"
        :key="index"
        :isCard="item.productId == 'A05' || item.productId == 'A04'"
        :isMyPage="true"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      ></ProductItem>
    </div>

    <div v-if="selectedCategory === 'category1'" class="mt-10">
      <ProductItem
        v-for="(item, index) in dataHandler(favoriteData)"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :items="item"
        :key="index"
        :isCard="item.productId == 'A05' || item.productId == 'A04'"
        :isMyPage="true"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      ></ProductItem>
    </div>

    <div v-else-if="selectedCategory === 'category2'" class="mt-10">
      <ProductItem
        v-for="(item, index) in dataHandler(favoriteData)"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :items="item"
        :key="index"
        :isCard="item.productId == 'A05' || item.productId == 'A04'"
        :isMyPage="true"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      ></ProductItem>
    </div>
    <div v-else-if="selectedCategory === 'category3'" class="mt-10">
      <ProductItem
        v-for="(item, index) in dataHandler(favoriteData)"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :items="item"
        :key="index"
        :isCard="item.productId == 'A05' || item.productId == 'A04'"
        :isMyPage="true"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      ></ProductItem>
    </div>
    <div v-else-if="selectedCategory === 'category4'" class="mt-10">
      <ProductItem
        v-for="(item, index) in dataHandler(favoriteData)"
        :index="index"
        :nowItemIndex="nowItemIndex"
        :items="item"
        :key="index"
        :isCard="item.productId == 'A05' || item.productId == 'A04'"
        :isMyPage="true"
        @click="
          () => {
            if (nowItemIndex == index) {
              nowItemIndex = null;
            } else {
              nowItemIndex = index;
            }
          }
        "
      ></ProductItem>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";
import ProductItem from "../AllProducts/ProductItem.vue";

const props = defineProps({
  favoriteData: {
    type: Object,
    required: true,
  },
});

const router = useRouter();
const selectedCategory = ref("category0");
const nowItemIndex = ref(null); // 현재 아이템 인덱스

const selectHandler = () => {
  nowItemIndex.value = null;
};

const dataHandler = data => {
  switch (selectedCategory.value) {
    case "category1":
      return data.filter(item => item.productId === "A05" || item.productId === "A04");
    case "category2":
      return data.filter(item => item.productId === "A01");
    case "category3":
      return data.filter(item => item.productId === "A03");
    case "category4":
      return data.filter(item => item.productId === "A02");
  }
};
// console.log(props.favoriteData);
</script>
