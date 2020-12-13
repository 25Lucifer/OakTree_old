package com.tree.blog.mapper;

import com.tree.blog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
@Repository
public interface UserMapper {

    User checkUser(String username,String password);

    User getUserById(@Param("id") Long id);

    User getUserByUsername(String username);

    long register(User user);

    int deleteUserById(Long id);

    User loadUserByUsername(@Param("username") String username);

    List<User> getUserByNickname(@Param("nickname") String nickname);

    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);


}
