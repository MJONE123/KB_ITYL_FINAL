<template>
  <div class="flex flex-col justify-center w-1/2 h-full">
    <font-awesome-icon icon="quote-left" class="w-8 h-8 mb-8" />
    <div class="px-16">
      <p class="text-[30px] py-2">
        전반적으로
        <span class="text-kb-blue-3 font-bold text-[34px]">{{
          data.text1
        }}</span>
        이 강하고
      </p>
      <p class="text-[30px] py-2">
        <span class="text-kb-blue-3 font-bold text-[34px]">{{
          data.text2
        }}</span>
        을 가지고 있습니다.
      </p>
      <p class="text-[30px] py-2">
        자산 대비
        <span class="text-kb-blue-3 font-bold text-[34px]">{{
          data.text3
        }}</span>
        을 보이며,
      </p>
      <p class="text-[30px] py-2">
        <span class="text-kb-blue-3 font-bold text-[34px]">{{
          data.text4
        }}</span>
        을 가지고 있습니다.
      </p>
    </div>
    <font-awesome-icon icon="quote-right" class="w-8 h-8 mt-8 ml-[580px]" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useAuthStore } from '../../stores/auth';
import { loadingStateStore } from '../../stores/loadingStateStore';
// 데이터 초기 상태 정의
const data = ref({
  text1: '투자 성향',
  text2: '알 수 없음',
  text3: '알 수 없음',
  text4: '알 수 없음',
});

const loadingStore = loadingStateStore();

// axios 연결 및 데이터 가져오기 함수 정의
const fetchUserSavingData = async () => {
  const authStore = useAuthStore();
  const token = authStore.token;

  if (!token) {
    console.error('토큰이 없습니다. 로그인 후 다시 시도하세요.');
    return;
  }

  loadingStore.setIsAssetAnalyzeLoading(true, 3);

  try {
    const response = await axios.post(
      `http://localhost:8080/assets/saving`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }
    );

    loadingStore.setIsAssetAnalyzeLoading(false, 3);

    // 응답 데이터 처리
    const resultMap = response?.data?.resultMap;
    if (!resultMap) {
      throw new Error('유효한 데이터를 가져오지 못했습니다.');
    }

    //데이터 셀렉
    // 방어형 투자 성향,성장형 투자성향
    // 안정 추구형 투자 성향, 고위험추구형 투자성향
    // 받아온 데이터를 사용하여 Vue의 상태 업데이트
    data.value.text1 = resultMap['사용자가 강한 성향'] || '투자 성향';
    data.value.text2 =
      resultMap['공격적인 성향'] === false
        ? '안정 추구형 투자 성향'
        : '위험 부담형 투자 성향';
    data.value.text3 = resultMap['자산 대비'][1] || '알 수 없음';
    data.value.text4 = (() => {
      const 성향 = resultMap['자산 대비'][0] || '알 수 없음';

      // '저축 성향', '투자 성향', '소비 성향'에 따라 문장 변환
      if (성향.includes('저축 성향')) {
        return '강한 저축 성향';
      } else if (성향.includes('투자 성향')) {
        return '강한 투자 성향';
      } else if (성향.includes('소비성향')) {
        return '강한 소비 성향';
      } else {
        return 성향;
      }
    })();
  } catch (error) {
    console.error('데이터 오류', error);
  }
};

// 컴포넌트가 마운트될 때 데이터 가져오기
onMounted(fetchUserSavingData);
</script>
