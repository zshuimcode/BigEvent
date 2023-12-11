<script setup>
import { Plus, Upload } from "@element-plus/icons-vue";
import { ref } from "vue";
import avatar from "@/assets/default.png";
import { useUserStore } from "@/stores/modules/user";
import { upload } from "@/api/article";
import { getUserInfoAPI, updateUserAvatarAPI } from "@/api/user";
import { ElMessage } from "element-plus";

import { useRouter } from "vue-router";
defineOptions({
  name: "更换头像",
});
const router = useRouter();
const userStore = useUserStore();
const uploadRef = ref();

//用户头像地址
const imgUrl = ref(
  userStore.userInfo.value.userPic ? userStore.userInfo.value.userPic : avatar
);

//上传图片成功回调
const uploadSuccess = (img) => {
  imgUrl.value = img.data.url;
};

const updateUserAvatar = async () => {
  const res = await updateUserAvatarAPI({ avatarUrl: imgUrl.value });
  if (res) {
    ElMessage({
      type: "success",
      message: "修改成功",
    });
    const res_info = await getUserInfoAPI();
    userStore.userInfo.value = res_info.data;
    router.push("/");
  }
};
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>更换头像</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-upload
          ref="uploadRef"
          class="avatar-uploader"
          :show-file-list="false"
          :auto-upload="true"
          :http-request="upload"
          :on-success="uploadSuccess"
        >
          <img v-if="imgUrl" :src="imgUrl" class="avatar" />
          <img v-else :src="avatar" width="278" />
        </el-upload>
        <br />
        <el-button
          type="primary"
          :icon="Plus"
          size="large"
          @click="uploadRef.$el.querySelector('input').click()"
        >
          选择图片
        </el-button>
        <el-button type="success" :icon="Upload" size="large" @click="updateUserAvatar">
          上传头像
        </el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep() {
    .avatar {
      width: 278px;
      height: 278px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 278px;
      height: 278px;
      text-align: center;
    }
  }
}
</style>
