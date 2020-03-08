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
}
