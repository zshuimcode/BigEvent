<script setup>
import { upload } from "@/api/article.js";
import { ref } from "vue";
import { Plus } from "@element-plus/icons-vue";
const headers = ref({
  "Content-Type": "multipart/form-data",
  Authorization: "WEH1YuKIpsYbpOF445rU0mkIe46ylk1A",
});



const visible = ref(false)
</script>
<!-- action="/api1/upload"
        :headers="headers"
        method="post" -->
<template>

  <el-popconfirm title="Are you sure to delete this?">
    <template #reference>
      <el-button>Delete</el-button>
    </template>
  </el-popconfirm>
</template>

<style scoped>
:deep(.el-upload) {
  position: relative;
  border-radius: 50%;
}
.avatar {
  position: absolute;
  z-index: 1;
}
.mask {
  z-index: 5;
  opacity: 0;
  background-color: #0a0a0a;
}
.mask:hover {
  opacity: 0.5;
}

.avatar-uploader {
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
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
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}

.el-button + .el-button {
  margin-left: 8px;
}
</style>
