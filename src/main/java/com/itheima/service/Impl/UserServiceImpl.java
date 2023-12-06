package com.itheima.service.Impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        // 密码加密存储
        String md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username, md5String);
    }


    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 更新用户头像
     *
     * @param avatarUrl
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String ,Object> map  = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    /**
     * 更新密码
     * @param newPwd
     */
    @Override
    public void updatePwd(String newPwd) {
        Map<String ,Object> map  = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
