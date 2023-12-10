package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

/**
 * 新增分类
 */
public interface CategoryService {
    /**
     * 添加 category 分类
     * @param category
     */
    void add(Category category);

    /**
     * 获取 category列表
     * @return
     */
    List<Category> getList();

    /**
     * 通过 id 获取 category 信息
     * @param id
     * @return
     */
    Category getCategorybyId(Number id);

    /**
     * 更新 category 分类
     * @param category
     */
    void update(Category category);

    /**
     * 删除分类
     * @param id
     */
    void Delete(Integer id);
}
