<template>
    <div class="w-1/5 p-8 flex flex-col items-center">
        <ul class="space-y-8 text-lg text-font-color ml-[40px]">
          <li>
            <button @click="editProfile" class=" hover:bg-kb-yellow-4 hover:rounded-md focus:outline-none text-[20px] p-3">프로필 수정</button>
          </li>
          <li>
            <button @click="editPassword" class="hover:bg-kb-yellow-4  hover:rounded-md focus:outline-none text-[20px] p-3">비밀번호 변경</button>
          </li>
          <li>
            <button @click="withdrawMembership" class="hover:bg-kb-yellow-4 hover:rounded-md focus:outline-none text-[20px] p-3">회원탈퇴</button>
          </li>
        </ul>
      </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

  const router=useRouter();

  const editProfile = () => {
    router.push('/mypage-profile')
  }
  const editPassword = () =>  router.push('/mypage-password')
  const withdrawMembership = () => router.push('/mypage-withdraw')
</script>