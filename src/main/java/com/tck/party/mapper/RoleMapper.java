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
}
