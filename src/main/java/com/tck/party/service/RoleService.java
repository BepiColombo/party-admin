package com.tck.party.service;

import com.tck.party.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查找所有的角色记录
     *
     * @return
     */
    List<Role> findRoleList();

    /**
     * 更新用户角色
     * @param userId
     * @param roleId
     * @return
     */
    int updateUserRole(Integer userId,Integer roleId);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRole(Integer roleId);

    /***
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 插入角色记录
     * @param role
     * @return
     */
    int insertRole(Role role);
}
