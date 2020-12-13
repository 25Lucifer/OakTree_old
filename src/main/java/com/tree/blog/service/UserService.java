package com.tree.blog.service;

import com.tree.blog.po.User;

/**
 * @author lucifer
 */
public interface UserService {

    User checkUser(String username, String password);

    User getUserByUsername(String username);


}
