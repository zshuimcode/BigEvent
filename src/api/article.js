import { request } from "@/utils";
import axios from "axios";
/**
 * 获取文章分类列表
 * @returns
 */
export const getCategoryList = () => {
  return request({
    url: "/category",
    method: "get",
  });
};

/**
 * 添加 文章分类
 * @param {*} catgory
 * @returns
 */
export const addCategoryAPI = (catgory) => {
  return request({
    url: "/category",
    method: "post",
    data: catgory,
  });
};
/**
 * 编辑 文章分类
 * @param {*} catgory
 * @returns
 */
export const editCategoryAPI = (catgory) => {
  return request({
    url: "/category",
    method: "put",
    data: catgory,
  });
};

/**
 * 删除 分类
 * @param {*} id
 * @returns
 */
export const delCategoryAPI = (id) => {
  return request({
    url: `/category?id=${id}`,
    method: "delete",
  });
};

/**
 * 获取文章列表
 * @param {*} queryObj
 */
export const getArticleList = (param) => {
  return request({
    url: `/article`,
    method: "get",
    params: param,
  });
};

export const upload = async (file) => {
  const formData = new FormData();
  formData.append("smfile", file.file);
  const res = await axios.post("/api1/upload", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "WEH1YuKIpsYbpOF445rU0mkIe46ylk1A",
    },
    timeout: 15000,
  });
  return res.data;
};

/**
 * 添加文章
 * @param {*} params
 */
export const addArticleAPI = (params) => {
  return request({
    method: "post",
    url: "/article",
    data: params,
  });
};
/**
 * 获取文章信息
 * @param {*} id 
 * @returns 
 */
export const getArticleAPI = (id) => {
  return request({
    url: "/article/detail",
    method: "get",
    params: { id },
  });
};

/**
 * 删除文章
 * @param {*} id 
 * @returns 
 */
export const delArticleAPI = (id) => {
  return request({
    url: "/article",
    method: "delete",
    params: { id },
  });
};
/**
 * 更新文章信息
 * @param {*} params 
 */
export const editArticleAPI = (params) => {
  // console.log(params);
  return request({
    method: 'put',
    url: '/article',
    data:params
  })
}