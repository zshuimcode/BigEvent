<script setup>
defineOptions({
  name: "ArticleCategory",
});
import { Edit, Delete } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";

import {
  getCategoryList,
  addCategoryAPI,
  editCategoryAPI,
  delCategoryAPI,
} from "@/api/article";

const categorys = ref([]);
/**
 * 获取 分类列表
 */
const getList = async () => {
  const res = await getCategoryList();
  if (res) {
    categorys.value = res.data;
  }
};

//控制添加分类弹窗
const dialogVisible = ref(false);
const addForm = ref();

//添加分类数据模型
const categoryModel = ref({
  categoryName: "",
  categoryAlias: "",
});
//添加分类表单校验
const rules = {
  categoryName: [
    { required: true, message: "请输入分类名称", trigger: "blur" },
  ],
  categoryAlias: [
    { required: true, message: "请输入分类别名", trigger: "blur" },
  ],
};
/**
 * 添加 分类
 * @param {*} addFormObj
 */
const addCategory = (addFormObj) => {
  if (!addFormObj) return;
  addFormObj.validate(async (valid, fields) => {
    if (valid) {
      const res = await addCategoryAPI(categoryModel.value);
      if (res) {
        ElMessage({
          type: "success",
          message: "添加成功",
        });
        closeDialog();
        getList();
      }
    } else {
    }
  });
};
/**
 * 关闭 添加弹窗 并 清除数据
 */
const closeDialog = () => {
  dialogVisible.value = false;
  categoryModel.value = {
    categoryName: "",
    categoryAlias: "",
  };
};

const title = ref("");
/**
 * 打开 编辑弹窗
 * @param {*} category
 */
const openEditCategory = (category) => {
  title.value = "编辑弹层";
  dialogVisible.value = true;
  const { id, categoryName, categoryAlias } = category;
  categoryModel.value.id = id;
  categoryModel.value.categoryName = categoryName;
  categoryModel.value.categoryAlias = categoryAlias;
};
/**
 * 编辑 分类
 */
const editCategory = (addFormObj) => {
  if (!addFormObj) return;
  addFormObj.validate(async (valid, fields) => {
    if (valid) {
      const res = await editCategoryAPI(categoryModel.value);
      if (res) {
        ElMessage({
          type: "success",
          message: res.message,
        });
        closeDialog();
        getList();
      }
    }
  });
};
/**
 * 删除 分类
 * @param {*} id
 */
const delCategory = (id) => {
  ElMessageBox.confirm("是否删除次分类?", "Warning", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    type: "warning",
  })
    .then(async () => {
      const res = await delCategoryAPI(id);
      if (res) {
        ElMessage({
          type: "success",
          message: "Delete completed",
        });
        getList();
      }
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Delete canceled",
      });
    });
};
onMounted(() => {
  getList();
});
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章分类</span>
        <div class="extra">
          <el-button
            type="primary"
            @click="
              dialogVisible = true;
              title = '添加弹层';
            "
            >添加分类</el-button
          >
        </div>
      </div>
    </template>
    <!-- 分类列表表格 start-->
    <el-table :data="categorys" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="分类名称" prop="categoryName"></el-table-column>
      <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button
            :icon="Edit"
            circle
            plain
            type="primary"
            @click="openEditCategory(row)"
          ></el-button>
          <el-button
            :icon="Delete"
            circle
            plain
            type="danger"
            @click="delCategory(row.id)"
          ></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
    <!-- 分类列表表格 end-->

    <!-- 添加/编辑 弹窗 start  -->
    <el-dialog
      v-model="dialogVisible"
      :title="title"
      width="30%"
      @close="closeDialog"
    >
      <el-form
        :model="categoryModel"
        :rules="rules"
        label-width="100px"
        style="padding-right: 30px"
        ref="addForm"
      >
        <el-form-item label="分类名称" prop="categoryName">
          <el-input
            v-model="categoryModel.categoryName"
            minlength="1"
            maxlength="10"
          ></el-input>
        </el-form-item>
        <el-form-item label="分类别名" prop="categoryAlias">
          <el-input
            v-model="categoryModel.categoryAlias"
            minlength="1"
            maxlength="15"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取消</el-button>
          <el-button
            type="primary"
            @click="
              title === '添加弹层'
                ? addCategory(addForm)
                : editCategory(addForm)
            "
          >
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 添加/编辑 弹窗 end  -->
  </el-card>
</template>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>
