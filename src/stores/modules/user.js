import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore(
  "user",
  () => {
    const userToken = ref("");
    const userInfo = ref({});
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

    /**
     * 移除用户信息
     */
    const removeUserInfo = () => {
      userInfo.value = {};
    };

    return {
      userToken,
      setToken,
      removeToken,
      userInfo,
      removeUserInfo,
    };
  },
  {
    persist: true,
  }
);
