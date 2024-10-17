<template>
  <div class="absolute flex items-center justify-center w-screen h-screen bg-white">
    <div class="relative flex items-center justify-center">
      <div class="w-[700px] h-[500px] bg-kb-yellow-3 blur-3xl rounded-full"></div>

      <div class="absolute">
        <div class="loader"></div>
        <p class="mt-10 text-[32px] font-semibold">{{`성향에 맞는 카드를 분석중${dot}`}}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const dot = ref('.');

setInterval(()=>{
  if(dot.value.length<3){
    dot.value += '.'
  }else{
    dot.value='.'
  }
}, 500)
</script>

<style scoped>
.loader {
  width: 48px;
  height: 48px;
  margin: auto;
  position: relative;
}

.loader:before {
  content: "";
  width: 48px;
  height: 5px;
  background: #f0808050;
  position: absolute;
  top: 60px;
  left: 0;
  border-radius: 50%;
  animation: shadow324 0.5s linear infinite;
}

.loader:after {
  content: "";
  width: 100%;
  height: 100%;
  background: #f08080;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 4px;
  animation: jump7456 0.5s linear infinite;
}

@keyframes jump7456 {
  15% {
    border-bottom-right-radius: 3px;
  }

  25% {
    transform: translateY(9px) rotate(22.5deg);
  }

  50% {
    transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 40px;
  }

  75% {
    transform: translateY(9px) rotate(67.5deg);
  }

  100% {
    transform: translateY(0) rotate(90deg);
  }
}

@keyframes shadow324 {
  0%,
  100% {
    transform: scale(1, 1);
  }

  50% {
    transform: scale(1.2, 1);
  }
}
</style>
