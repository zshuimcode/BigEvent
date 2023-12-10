<script setup>
import { User, Lock } from "@element-plus/icons-vue";
import { ref } from "vue";
import { userRegisterAPI, userLoginAPI } from "@/api/user";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/modules/user";
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false);
const router = useRouter();
const userStore = useUserStore();
defineOptions({
  name: "Login",
});

const registerData = ref({
  username: "",
  password: "",
  rePassword: "",
});

/**
 * 校验两次密码是否相同
 * @param {*} rule
 * @param {*} value
 * @param {*} callback
 */
const ComparePasswords = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("两次密码不相同"));
  } else if (value !== registerData.value.password) {
    callback(new Error("两次密码不相同"));
  } else {
    callback();
  }
};
const registerDataRules = {
  username: [
    {
      required: true,
      message: "用户名不能为空",
      trigger: "blur",
    },
    {
      min: 5,
      max: 16,
      message: "用户名在5-16位",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: "blur",
    },
    {
      min: 5,
      max: 16,
      message: "密码在5-16位",
      trigger: "blur",
    },
  ],
  rePassword: [
    {
      validator: ComparePasswords,
      trigger: "blur",
    },
  ],
};

const registerRef = ref();
/**
 * 注册
 * @param {*} registerRefObj
 */
const handlerRegister = async (registerRefObj) => {
  if (!registerRefObj) return;
  await registerRefObj.validate(async (valid, fields) => {
    if (valid) {
      const res = await userRegisterAPI(registerData.value);
      if (res) {
        ElMessage({
          type: "success",
          message: "注册成功",
        });
        clearData();
      }
      // 验证通过执行
    } else {
      // 验证不通过 留着到时候看
    }
  });
};

const loginRef = ref();
/**
 * 登录
 * @param {*} loginRefObj
 */
const handlerLogin = async (loginRefObj) => {
  if (!loginRefObj) return;
  await loginRefObj.validate(async (valid, fields) => {
    if (valid) {
      const res = await userLoginAPI(registerData.value);
      if (res) {
        ElMessage({
          type: "success",
          message: "登录成功",
        });
        userStore.setToken(res.data);
        router.push("/");
      }
    } else {
    }
  });
};

/**
 * 转换注册和登录界面清空数据
 */
const clearData = () => {
  registerData.value = {
    username: "",
    password: "",
    rePassword: "",
  };
  isRegister.value = !isRegister.value;
};
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form
        ref="registerRef"
        size="large"
        autocomplete="off"
        v-if="isRegister"
        :model="registerData"
        :rules="registerDataRules"
      >
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="请输入用户名"
            v-model="registerData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入密码"
            v-model="registerData.password"
          ></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入再次密码"
            v-model="registerData.rePassword"
          ></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="handlerRegister(registerRef)"
          >
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="clearData">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form
        ref="loginRef"
        size="large"
        autocomplete="off"
        v-else
        :model="registerData"
        :rules="registerDataRules"
      >
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="请输入用户名"
            v-model="registerData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            name="password"
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入密码"
            v-model="registerData.password"
          ></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="handlerLogin(loginRef)"
            >登录</el-button
          >
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="clearData">
            注册 →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url("@/assets/logo2.png") no-repeat 60% center / 240px auto,
      url("@/assets/login_bg.jpg") no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
