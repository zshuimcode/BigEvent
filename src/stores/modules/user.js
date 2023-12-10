import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore(
  "user",
  () => {
    const userToken = ref("");
    /**
     * 设置token
     * @param {*} newToken 
     */
    const setToken = (newToken) => {
      userToken.value = newToken;
    };
    /**
     * 移除token
     */
    const removeToken = () => {
      userToken.value = "";
    };
    return {
      userToken,
      setToken,
      removeToken,
    };
  },
  {
    persist: true,
  }
);
