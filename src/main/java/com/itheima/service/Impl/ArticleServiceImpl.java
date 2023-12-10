package com.itheima.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.mypojo.ConditionalQueryArticle;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 新增文章
     *
     * @param article
     */
    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateUser(id);
        articleMapper.add(article);
    }


    /**
     * 条件获取 article 列表
     *
     * @param conditionalQueryArticle
     * @return
     */
    @Override
    public Map<String, Object> list(ConditionalQueryArticle conditionalQueryArticle) {
        PageHelper.startPage(conditionalQueryArticle.getPageNum(), conditionalQueryArticle.getPageSize());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(conditionalQueryArticle.getCategoryId(), conditionalQueryArticle.getState(),id);

        Page<Article> p = (Page<Article>) articles;


        Map<String, Object> articleMap = new HashMap<>();
        articleMap.put("total",p.getTotal());
        articleMap.put("items",p.getResult());
        return articleMap;
    }

    /**
     * 获取文章信息
     * @param id
     * @return
     */
    @Override
    public Article Info(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUserId = (Integer) map.get("id");

        Article article = articleMapper.Info(id,createUserId);
        return article;
    }

    /**
     * 更新文章
     * @param article
     */
    @Override
    public void Update(Article article) {
        articleMapper.Update(article);
    }

    /**
     * 删除文章
     * @param id
     */
    @Override
    public void Delete(Integer id) {
        articleMapper.Delete(id);
    }
}
