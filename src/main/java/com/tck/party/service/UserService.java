package com.tck.party.service;

import com.tck.party.common.vo.PageResult;
import com.tck.party.entity.User;

import java.util.Set;

public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    PageResult<User> findUsers(Integer pageNum, Integer pageSize);


    /**
     * 通过用户名获取用户
     */
    User findUserByUserName(String username);

    /**
     * 获取用户详情
     *
     * @param username
     * @return
     */
    User findUserDetail(String username);

    /**
     * 通过用户名获取用户角色集
     *
     * @param username
     * @return
     */
    Set<String> findUserRoles(String username);

    /**
     * 铜鼓用户名获取用户菜单\按钮权限集
     *
     * @param username
     * @return
     */
    Set<String> findUserPermissions(String username);

    /**
     * 添加用户
     *
     * @return
     */
    int insertUser(User user);
}
