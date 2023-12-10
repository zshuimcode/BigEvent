package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.mypojo.ConditionalQueryArticle;

import java.util.Map;

public interface ArticleService {

    /**
     * 新增文章
     * @param article
     */
    void add(Article article);

    /**
     * 条件获取 article 列表
     *
     * @param conditionalQueryArticle
     * @return
     */
    Map<String, Object> list(ConditionalQueryArticle conditionalQueryArticle);

    /**
     * 获取文章信息
     * @param id
     * @return
     */
    Article Info(Integer id);

    /**
     * 更新文章
     * @param article
     */
    void Update(Article article);

    /**
     * 删除 文章
     * @param id
     */
    void Delete(Integer id);
}
