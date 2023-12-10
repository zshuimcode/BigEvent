package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.Result;
import com.itheima.pojo.mypojo.ConditionalQueryArticle;
import com.itheima.service.ArticleService;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping()
    public Result<Map<String, Object>> list(ConditionalQueryArticle conditionalQueryArticle) {
        Map<String, Object> map = articleService.list(conditionalQueryArticle);
        return Result.success(map);
    }


    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Article> articleInfo(@RequestParam("id") Integer id) {
        Article article = articleService.Info(id);
        return Result.success(article);
    }

    @PutMapping
    public Result update(@RequestBody Article article) {
        articleService.Update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result Delete(@PathParam("id") Integer id) {
        articleService.Delete(id);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result<String> Upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String filename = UUID.randomUUID().toString() + originalFilename;
        multipartFile.transferTo(new File("F:\\" + filename));


        return Result.success();
    }

}
