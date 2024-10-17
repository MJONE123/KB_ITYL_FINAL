import { defineStore } from 'pinia';

export const loadingStateStore = defineStore('loadingState', {
  state: () => ({
    isAssetAnalyzeLoadingState: [false, false, false, false],
    isAssetPlanLoadingState: false
  }),
  actions: {
    setIsAssetAnalyzeLoading(value, index) {
      this.isAssetAnalyzeLoadingState[index] = value;
    },
    setIsAssetPlanLoadingState(value){
      this.isAssetPlanLoadingState = value;
    }
  },
  getters: {
    isAssetAnalyzeLoading: (state) => state.isAssetAnalyzeLoadingState,
    isAssetPlanLoading: (state) => state.isAssetPlanLoadingState
  },
});
