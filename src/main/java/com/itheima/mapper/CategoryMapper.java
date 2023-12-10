package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    /**
     * 获取 category 分类列表
     * @return
     */
    @Select("select * from category where create_user = #{id}")
    List<Category> getList(Integer id);

    /**
     * 通过 id 获取详细信息
     * @param id
     * @param createUserID
     * @return
     */
    @Select("select  * from category where id=#{id} and create_user = #{createUserID}")
    Category getCategoryById(Number id, Integer createUserID);

    /**
     * 更新category 分类信息
     *
     * @param category
     */
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void Delete(Integer id);
}
