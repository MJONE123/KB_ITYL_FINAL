<template>
  <div :class="isMyPage ? '' : 'px-[20%]'">
    <div
      class="relative flex w-full gap-4 p-5 cursor-pointer hover:bg-gray-50"
      :class="index != nowItemIndex && 'border-b'"
    >
      <!-- 이미지 -->
      <div class="relative flex items-center justify-center w-20 h-20">
        <div
          class="absolute w-full h-full rounded-full bg-kb-yellow-3 blur-sm"
        ></div>
        <img
          :src="items.url.imageUrl"
          class="absolute top-[14px]"
          :class="isCard && 'rotate-90'"
          alt=""
        />
      </div>

      <!-- 내용 -->
      <div class="flex flex-col justify-center">
        <h1 class="text-[18px]">{{ items.name }}</h1>
        <h3 class="text-[14px] mt-2 text-kb-gray-1">{{ items.content }}</h3>
      </div>

      <div class="absolute right-3 top-[30%] flex items-center gap-2">
        <button
          class="p-2 ml-4 bg-white rounded-full shadow-lg"
          @click.stop="favoriteHandler"
        >
          <font-awesome-icon
            v-if="!items.isFavorite"
            :icon="['far', 'heart']"
            class="text-kb-yellow-3"
            size="xl"
          />
          <font-awesome-icon
            v-else
            :icon="['fas', 'heart']"
            class="text-kb-yellow-3"
            size="xl"
          />
        </button>
        <button>
          <!-- <font-awesome-icon :icon="['fas', 'chevron-down']" /> -->
          <svg
            v-if="index != nowItemIndex"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1"
            stroke="currentColor"
            class="size-6 text-kb-gray-1"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="m19.5 8.25-7.5 7.5-7.5-7.5"
            />
          </svg>

          <svg
            v-if="index == nowItemIndex"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1"
            stroke="currentColor"
            class="size-6 text-kb-gray-1"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="m4.5 15.75 7.5-7.5 7.5 7.5"
            />
          </svg>

          <!-- <font-awesome-icon :icon="['fas', 'chevron-up']" /> -->
        </button>
      </div>
    </div>

    <!-- 클릭시 콘텐츠 노출 -->
    <div
      :class="index != nowItemIndex && 'hidden'"
      class="border-b"
      @click.stop
    >
      <div class="flex">
        <div
          v-for="(item, key, itemIndex) in items.data"
          :key="key"
          class="flex-1 p-10 text-center"
          :class="itemIndex != dataLength && `border-r`"
        >
          <h3 class="text-kb-gray-1">{{ key }}</h3>
          <h3 class="text-[20px] mt-2">{{ item }}</h3>
        </div>
      </div>
      <div class="mb-4 text-center">
        <CommonButton :text="'상세보기'" @click="openPage" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import CommonButton from '../common/CommonButton.vue';
import { addFavorite, removeFavorite } from '../../apis/api/favorites';

const props = defineProps({
  items: {
    type: Object,
    required: true,
  },
  index: {
    type: Number,
    required: true,
  },
  nowItemIndex: {
    type: Number && null,
    required: true,
  },
  isCard: {
    type: Boolean,
    required: true,
  },
  isMyPage: {
    type: Boolean,
    required: false,
  },
});

const favoriteHandler = async () => {
  if (!localStorage.getItem('accessToken')) {
    alert('로그인 후 이용해주세요');
    return;
  }
  if (!props.items.isFavorite) {
    props.items.isFavorite = true;
    const res = await addFavorite(props.items.productId, props.items.id);
    // console.log(res);
  } else {
    props.items.isFavorite = false;
    const res = await removeFavorite(props.items.productId, props.items.id);
    // console.log(res);
  }
};

const dataLength = ref(Object.keys(props.items.data).length - 1);
// console.log(dataLength.value);

const openPage = () => {
  window.open(props.items.url.pageUrl);
};
</script>
