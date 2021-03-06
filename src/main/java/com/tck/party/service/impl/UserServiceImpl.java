package com.tck.party.service.impl;

import com.github.pagehelper.PageHelper;
import com.tck.party.common.base.PageResult;
import com.tck.party.service.dto.UserDto;
import com.tck.party.vo.UserManageParam;
import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import com.tck.party.mapper.RoleMapper;
import com.tck.party.mapper.UserMapper;
import com.tck.party.vo.UserQueryParam;
import com.tck.party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;


    /**
     * 查找所有用户
     *
     * @return
     */
    public PageResult<UserDto> findUsers(UserQueryParam userQueryParam) {
        PageHelper.startPage(userQueryParam.getPageNum(), userQueryParam.getPageSize());
        List<UserDto> users = userMapper.findUsers(userQueryParam);
        PageResult<UserDto> pageResult = new PageResult<>(users);
        return pageResult;
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
     * 获取用户详情
     *
     * @return
     */
    public UserDto findUserDetail(String username) {
        UserDto user = userMapper.findUserDetail(username);
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

    /**
     * 添加用户
     *
     * @return
     */
    public int insertUser(User user) {
        int res = userMapper.insertUser(user);

        //默认设置为普通用户 roleId为1
        this.setUserRoles(user.getUserId(), 1);
        return res;
    }

    /**
     * 通过userId删除用户
     *
     * @param userId
     * @return
     */
    public int deleteUserById(Integer userId) {
        int res = userMapper.deleteUserById(userId);
        return res;
    }

    /**
     * 更新用户(管理员管理时)
     *
     * @param userManageParam
     * @return
     */
    public int updateUserByManager(UserManageParam userManageParam) {
        int res = userMapper.updateUserByManager(userManageParam);
        return res;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    private void setUserRoles(Integer userId, Integer roleId) {
        roleMapper.insertUserRole(userId, roleId);
    }

}
