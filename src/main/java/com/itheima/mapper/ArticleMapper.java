package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 新增 文章
     *
     * @param article
     */
    @Insert("insert into article (title,content,cover_img,state,category_id,create_user,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


    /**
     * 条件获取 article 列表
     *
     * @param categoryId
     * @param state
     * @param id
     * @return
     */
    List<Article> list(Integer categoryId, String state, Integer id);

    /**
     * 获取文章信息
     *
     * @param id
     * @param createUserId
     * @return
     */
    @Select("select * from article where create_user=#{createUserId} and id=#{id}")
    Article Info(Integer id, Integer createUserId);

    /**
     * 更新文章
     * @param article
     */
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=now() where id =#{id}")
    void Update(Article article);

    /**
     * 删除文章
     * @param id
     */
    @Delete("delete from article where id = #{id}")
    void Delete(Integer id);
}
