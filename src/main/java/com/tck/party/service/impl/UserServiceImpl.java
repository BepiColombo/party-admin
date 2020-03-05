package com.tck.party.service.impl;

import com.github.pagehelper.PageHelper;
import com.tck.party.common.vo.PageResult;
import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import com.tck.party.mapper.RoleMapper;
import com.tck.party.mapper.UserMapper;
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
    public PageResult<User> findUsers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.findUsers();
        PageResult<User> pageResult = new PageResult<>(users);
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
    public User findUserDetail(String username) {
        User user = userMapper.findUserDetail(username);
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
     * 给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    private void setUserRoles(Integer userId, Integer roleId) {
        roleMapper.insertUserRole(userId, roleId);
    }

}
