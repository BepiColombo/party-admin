package com.tck.party.service.impl;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import com.tck.party.mapper.UserMapper;
import com.tck.party.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * 根据用户名获取用户角色集
     *
     * @param username
     * @return
     */
    public Set<String> findUserRoles(String username) {
        List<Role> roles = userMapper.findUserRole(username);
        return roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    /**
     * 根据用户名获取用户菜单\按钮权限集
     *
     * @param username
     * @return
     */
    public Set<String> findUserPermissions(String username) {
        List<Menu> menus = userMapper.findUserPermissions(username);
        return menus.stream().map(Menu::getPerms).collect(Collectors.toSet());
    }
}
