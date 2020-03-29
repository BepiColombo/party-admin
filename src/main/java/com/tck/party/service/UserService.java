package com.tck.party.service;

import com.tck.party.common.base.PageResult;
import com.tck.party.service.dto.UserDto;
import com.tck.party.vo.UserManageParam;
import com.tck.party.entity.User;
import com.tck.party.vo.UserQueryParam;

import java.util.Set;

public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    PageResult<UserDto> findUsers(UserQueryParam userQueryParam);


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
    UserDto findUserDetail(String username);

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
     * 更新用户(管理员管理时)
     * @param userManageParam
     * @return
     */
    int updateUserByManager(UserManageParam userManageParam);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);
}
