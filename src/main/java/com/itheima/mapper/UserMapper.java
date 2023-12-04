package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);


    /**
     * 添加  用户
     * @param username
     * @param password
     */
    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},now(),now()) ")
    void add(String username, String password);
}
