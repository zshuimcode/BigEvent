package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping()
    public Result add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> getList() {

        List<Category> list = categoryService.getList();
        return Result.success(list);

    }

    @GetMapping("/detail")
    public Result<Category> getCategoryById(@RequestParam(value = "id", required = true) @PathParam("id") Number id) {
        Category category = categoryService.getCategorybyId(id);
        return Result.success(category);
    }


    @PutMapping
    public Result update(@RequestBody @Validated Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public  Result DeleteCategory(@RequestParam(value = "id",required = true) Integer id){
        categoryService.Delete(id);
        return Result.success();
    }


}
