package com.tck.party.mapper;

import com.tck.party.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 插入一条用户-角色记录
     *
     * @param userId
     * @param roleId
     */
    @Insert("INSERT INTO p_role_user VALUES (#{userId},#{roleId})")
    void insertUserRole(Integer userId, Integer roleId);

    /**
     * 查找所有的角色记录
     * @return
     */
    @Select("SELECT id as roleId, role_name as roleName, role_description as roleDescription FROM P_ROLE")
    List<Role> findRoleList();
}
