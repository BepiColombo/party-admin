package com.tck.party.service;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.RoleMenu;

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
     *
     * @param userId
     * @param roleId
     * @return
     */
    int updateUserRole(Integer userId, Integer roleId);

    /**
     * 删除角色
     *
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
     *
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 批量操作role-menu记录
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean batchActionRoleMenu(Integer roleId, List<Integer> menuIds) throws Exception;

    /**
     * 批量插入role-menu记录
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertRoleMenuBatch(Integer roleId, List<Integer> menuIds);


    /**
     * 批量删除role-menu记录
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int deleteRoleMenuBatch(Integer roleId, List<Integer> menuIds);
}
