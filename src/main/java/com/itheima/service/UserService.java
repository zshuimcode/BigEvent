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

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 更新密码
     * @param newPwd
     */
    void updatePwd(String newPwd);
}
