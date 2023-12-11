<script setup>
import { onMounted, ref } from "vue";
import { updateUserInfoAPI, getUserInfoAPI } from "@/api/user";
import { useUserStore } from "@/stores/modules/user";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
defineOptions({
  name: "基本资料",
});

const router = useRouter();
const userStore = useUserStore();
const userInfo = ref({
  id: 0,
  username: "zhangsan",
  nickname: "zs",
  email: "zs@163.com",
});
const rules = {
  nickname: [
    { required: true, message: "请输入用户昵称", trigger: "blur" },
    {
      pattern: /^\S{2,10}$/,
      message: "昵称必须是2-10位的非空字符串",
      trigger: "blur",
    },
  ],
  email: [
    { required: true, message: "请输入用户邮箱", trigger: "blur" },
    { type: "email", message: "邮箱格式不正确", trigger: "blur" },
  ],
};

const updateUserInfo = async () => {
  const res = await updateUserInfoAPI(userInfo.value);
  if (res) {
    ElMessage({
      type: "success",
      message: "跟新成功",
    });
      const res_info = await getUserInfoAPI();
    userStore.userInfo.value = res_info.data;
    router.push("/");
  }
};

onMounted(() => {
  userInfo.value = userStore.userInfo.value;
});
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>基本资料</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-form
          :model="userInfo"
          :rules="rules"
          label-width="100px"
          size="large"
        >
          <el-form-item label="登录名称">
            <el-input v-model="userInfo.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户昵称" prop="nickname">
            <el-input v-model="userInfo.nickname"></el-input>
          </el-form-item>
          <el-form-item label="用户邮箱" prop="email">
            <el-input v-model="userInfo.email"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateUserInfo"
              >提交修改</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>
