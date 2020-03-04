package com.tck.party.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

    /**
     * 插入一条用户-角色记录
     * @param userId
     * @param roleId
     */
    @Insert("INSERT INTO p_role_user VALUES (#{userId},#{roleId})")
    void insertUserRole(Integer userId, Integer roleId);
}
