package com.tck.party.service;

import com.tck.party.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 添加用户
     * @return
     */
    int addUser(User user);

    /**
     * 通过用户名获取用户
     */
    User findUserByUserName(String username);
}
