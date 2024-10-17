<template>
  <div
    class="flex h-[300px] relative duration-150 gap-10"
    :class="`bg-[url('${items.cardImageUrl}')]`"
    :style="{
      width: isNow && isHover ? '450px' : '200px',
      transform: isNow && isHover ? 'translateX(-100px)' : 'none',
      cursor: currentIndex !== index ? 'pointer' : 'default',
    }"
  >
    <img
      :src="`${items.cardImageUrl}`"
      class=""
      :class="isCard"
      :alt="`${items.cardName}`"
    />

    <!-- opacity와 visibility에 조건부 transition 적용 -->
    <div
      class="flex flex-col flex-1 gap-6 text-left"
      :style="{
        opacity: isHover && isNow ? 1 : 0,
        visibility: isHover ? 'visible' : 'hidden', // hidden 대신 visibility 사용
        transition: isHover && isNow ? 'opacity 1000ms' : 'none', // opacity 애니메이션이 끝난 후 visibility 변경
      }"
    >
      <h1 class="font-bold text-kb-brown-1 text-[22px] whitespace-nowrap">
        {{ items.cardName }}
      </h1>
      <h3 class="text-kb-brown-1 text-[18px] whitespace-nowrap">
        {{ items.cardContent }}
      </h3>
      <div class="mt-4">
        <button
          class="text-white text-sm bg-kb-brown-1 rounded-[15px] px-4 py-3"
          @click="openPage"
        >
          자세히 보기
        </button>
        <button class="px-2 py-1.5 ml-4 bg-white rounded-full" @click="()=>{
          isFavorite=!isFavorite
        }">
          <font-awesome-icon
          v-if="!isFavorite"
            :icon="['far', 'heart']"
            class="text-kb-yellow-3"
            size="lg"
          />
          <font-awesome-icon
          v-else
            :icon="['fas', 'heart']"
            class="text-kb-yellow-3"
            size="lg"
          />
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  items: {
    type: Object,
    required: true,
  },
  isHover: {
    type: Boolean,
    required: true,
  },
  isNow: {
    type: Boolean,
    required: true,
  },
  isCard: {
    type: Boolean,
    required: true,
  },
  currentIndex: {
    type: Number,
    required: true,
  },
  index: {
    type: Number,
    required: true,
  },
});

const isFavorite = ref(false);

const openPage = () => {
  window.open(props.items.pageUrl);
};
</script>
