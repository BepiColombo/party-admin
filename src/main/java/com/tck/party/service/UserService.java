package com.tck.party.service;

import com.tck.party.common.vo.PageResult;
import com.tck.party.entity.User;
import com.tck.party.query.UserQuery;

import java.util.Set;

public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    PageResult<User> findUsers(UserQuery userQuery);


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

    /**
     * 通过userId删除用户
     * @param userId
     * @return
     */
    int deleteUserById(Integer userId);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);
}
