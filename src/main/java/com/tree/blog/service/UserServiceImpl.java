package com.tree.blog.service;

import com.tree.blog.mapper.UserMapper;
import com.tree.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /*
    后面加上security，去掉这个方法
     */
    @Override
    public User checkUser(String username, String password) {
        return userMapper.checkUser(username,password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
