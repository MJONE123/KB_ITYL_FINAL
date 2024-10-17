import { defineStore } from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('accessToken') || null,
    name: localStorage.getItem('name') || null,  
    id: localStorage.getItem('id') || null,      
  }),
  getters: {
    isLoggedIn: (state) => !!state.token,
  },
  actions: {
    setToken(token) {
      this.token = token;
      localStorage.setItem('accessToken', token);
    },
    setUserInfo({ name,id }) {
      console.log('저장할 사용자 정보:', name, id);  
      this.name = name;
      this.id = id;
      localStorage.setItem('name', name); 
      localStorage.setItem('id',id);     
    },
    logout() {
      this.token = null;
      this.userId = null;
      this.userName = null;
      localStorage.removeItem('accessToken');
      localStorage.removeItem('name');  
      localStorage.removeItem('id');    
    },

    // 사용자 프로필 API 호출 후 정보 설정
    async fetchUserProfile() {
      try {
        const response = await axios.get(`http://localhost:8080/api/user/profile`, {
          headers: {
            Authorization: `Bearer ${this.token}`, 
          },
        });
        const userData = response.data;
        console.log('API 응답 상태:', response.status);  
        console.log('API 응답 데이터:', response.data);  

        this.setUserInfo({
          name: userData.name,  
          id: userData.id,      
        });
      } catch (error) {
        console.error('사용자 프로필을 가져오는 중 오류 발생:', error);
      }
    },
  },
});
