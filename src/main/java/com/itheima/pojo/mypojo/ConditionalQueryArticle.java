package com.itheima.pojo.mypojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConditionalQueryArticle {
    private Integer pageNum;
    private Integer pageSize;
    private Integer categoryId;
    private String state;

}
