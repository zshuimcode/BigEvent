package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {

    /**
     * 用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);
}
