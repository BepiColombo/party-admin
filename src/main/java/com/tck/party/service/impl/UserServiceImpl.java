package com.tck.party.service.impl;

import com.tck.party.entity.User;
import com.tck.party.mapper.UserMapper;
import com.tck.party.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 查找所有用户
     *
     * @return
     */
    public List<User> findAllUser() {
        List<User> users = userMapper.findAll();
        return users;
    }

    ;

    /**
     * 添加用户
     *
     * @return
     */
    public int addUser(User user) {
        int res = userMapper.insertUser(user);
        return res;
    }

    /**
     * 通过用户名获取用户信息
     *
     * @return
     */
    public User findUserByUserName(String username) {
        User user = userMapper.findUserByUserName(username);
        return user;
    }
}
