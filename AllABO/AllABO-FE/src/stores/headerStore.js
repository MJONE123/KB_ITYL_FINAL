import { defineStore } from 'pinia'

export const useHeaderStore = defineStore('header', {
  state: () => ({
    isScrolled: false,
    isHovered: false,
  }),
  actions: {
    setScrolled(value) {
      this.isScrolled = value
    },
    setHovered(value) {
        this.isHovered = value
    },
    resetState() {
        this.isScrolled = false
        this.isHovered = false
    }
  },
  getters: {
    isActive: (state) => state.isScrolled || state.isHovered
  }
})