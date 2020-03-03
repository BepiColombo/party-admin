package com.tck.party.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tck.party.entity.Menu;
import com.tck.party.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    PageInfo<User> findUsers(Integer pageNum, Integer pageSize);

    /**
     * 添加用户
     *
     * @return
     */
    int addUser(User user);

    /**
     * 通过用户名获取用户
     */
    User findUserByUserName(String username);

    /**
     * 通过用户名获取用户角色集
     * @param username
     * @return
     */
    Set<String> findUserRoles(String username);

    /**
     * 铜鼓用户名获取用户菜单\按钮权限集
     * @param username
     * @return
     */
    Set<String> findUserPermissions(String username);
}
