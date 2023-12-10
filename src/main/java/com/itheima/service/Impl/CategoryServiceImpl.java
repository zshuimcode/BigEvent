package com.itheima.service.Impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 新增分类
     *
     * @param category
     */
    @Override
    public void add(Category category) {

        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }


    /**
     * 获取 category 分类列表
     * @return
     */
    @Override
    public List<Category> getList() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Category> list =  categoryMapper.getList(id);
        return list;
    }

    /**
     * 通过 id 获取category 信息
     * @param id
     * @return
     */
    @Override
    public Category getCategorybyId(Number id) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer createUserID = (Integer) map.get("id");
        Category category = categoryMapper.getCategoryById(id,createUserID);
        return category;
    }

    /**
     * 更新category 分类
     * @param category
     */
    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void Delete(Integer id) {
        categoryMapper.Delete(id);
    }
}
