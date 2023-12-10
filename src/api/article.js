import { request } from "@/utils";
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