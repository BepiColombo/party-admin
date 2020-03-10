package com.tck.party.mapper;

import com.tck.party.entity.Role;
import com.tck.party.entity.RoleMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    /**
     * 插入一条用户-角色记录
     *
     * @param userId
     * @param roleId
     */
    void insertUserRole(Integer userId, Integer roleId);

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
     * 插入一条角色记录
     *
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    int deleteRole(Integer roleId);

    /**
     * 批量插入role-menu记录
     *
     * @param roleMenus
     * @return
     */
    int insertRoleMenuBatch(List<RoleMenu> roleMenus);

    /**
     * 批量删除role-menu记录
     *
     * @return
     */
    int deleteRoleMenuBatch(Map param);
}
