import { defineStore } from 'pinia';

export const profileStateStore = defineStore('profileState', {
  state: () => ({
    imageUrl : '/images/Mypage/user.png'
  }),
  actions: {
    setImageUrl(value) {
      this.imageUrl = value;
    },
  },
  getters: {
    getImageUrl: (state) => state.imageUrl,
  },
});
