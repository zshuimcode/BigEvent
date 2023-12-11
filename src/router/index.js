import { createWebHistory, createRouter } from "vue-router";

import Login from "@/views/Login/index.vue";
import Layout from "@/views/Layout/index.vue";
import ArticleCategory from "@/views/article/ArticleCategory.vue";
import ArticleManage from "@/views/article/ArticleManage.vue";
import UserAvatar from "@/views/user/UserAvatar.vue";
import UserInfo from "@/views/user/UserInfo.vue";
import UserResetPassword from "@/views/user/UserResetPassword.vue";
import Test from "@/views/test/index.vue";


const routes = [
  { path: "/login", component: Login },
  { path: "/test", component: Test },
  {
    path: "/",
    component: Layout,
    redirect: "/article/category",
    children: [
      { path: "article/category", component: ArticleCategory },
      { path: "article/manage", component: ArticleManage },
      { path: "user/info", component: UserInfo },
      { path: "user/avatar", component: UserAvatar },
      { path: "user/resetPassword", component: UserResetPassword },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

router.beforeEach((to, from) => {
  if (
    to.path !== "/login" &&
    JSON.parse(localStorage.getItem("user")).userToken === ""
  ) {
    return "/login";
  }
});

export default router;
