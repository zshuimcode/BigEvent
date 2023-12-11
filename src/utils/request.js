import axios from "axios";
import { useUserStore } from "@/stores/modules/user";
import { useRouter } from "vue-router";
import router from "@/router";

const request = axios.create({
  baseURL: "/firstapi",
  timeout: 5000,
});

request.interceptors.request.use(
  (config) => {
    const userStore = useUserStore();
    if (userStore.userToken) config.headers.token = userStore.userToken;
    return config;
  },
  (error) => {
    // 对错误请求处理
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  (response) => {
    if (response.data.code === 0) {
      return response.data;
    } else {
      ElMessage({
        type: "error",
        message: response.data.message,
      });
    }
  },
  (error) => {
    
    switch (error.response.status) {
      case 401: {
        ElMessage({
          type: "error",
          message: "请先登录",
        });
        // const router = useRouter();
        router.replace("/login");
        break;
      }
      default: {
        ElMessage({
          type: "warning",
          message: error.response.data.msg,
        });
      }
    }
    return Promise.reject(error);
  }
);

export default request;
