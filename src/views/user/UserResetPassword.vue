<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { ref } from "vue";
import { updateUserPasswordAPI } from "@/api/user";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/modules/user";
defineOptions({
  name: "重置密码",
});
const pwdFormRef = ref();
const userStore = useUserStore();
const router = useRouter();
const pwdForm = ref({
  old_pwd: "",
  new_pwd: "",
  re_pwd: "",
});
/**
 * 校验两次密码是否相同
 * @param {*} rule
 * @param {*} value
 * @param {*} callback
 */
const comparePwd = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("密码不能为空"));
  } else if (value !== pwdForm.value.new_pwd) {
    callback(new Error("两次密码不相同"));
  } else {
    callback();
  }
};
// 规则
const pwdFormRules = {
  old_pwd: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, max: 16, message: "请输入6-16位非空字符", trigger: "blur" },
  ],
  new_pwd: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, max: 16, message: "请输入6-16位非空字符", trigger: "blur" },
  ],
  re_pwd: [{ validator: comparePwd, trigger: "blur" }],
};

/**
 * 修改密码
 * @param {*} pwdFormRefObj
 */
const updateUserPwd = (pwdFormRefObj) => {
  if (!pwdFormRefObj) return;
  pwdFormRefObj.validate(async (valid) => {
    if (valid) {
      const res = await updateUserPasswordAPI(pwdForm.value);
      if (res) {
        ElMessage({
          type: "success",
          message: "修改成功",
        });
        userStore.removeToken();
        userStore.removeUserInfo();
        router.replace("/login");
      }
    }
  });
};
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
          label-position="right"
          label-width="100px"
          :model="pwdForm"
          style="max-width: 460px"
          :rules="pwdFormRules"
          ref="pwdFormRef"
        >
          <el-form-item label="旧密码" prop="old_pwd">
            <el-input v-model="pwdForm.old_pwd" />
          </el-form-item>
          <el-form-item label="新密码" prop="new_pwd">
            <el-input v-model="pwdForm.new_pwd" />
          </el-form-item>
          <el-form-item label="再次输入密码" prop="re_pwd">
            <el-input v-model="pwdForm.re_pwd" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateUserPwd(pwdFormRef)"
              >提交修改</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<style scoped></style>
