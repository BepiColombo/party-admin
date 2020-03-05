package com.tck.party.mapper;

import com.tck.party.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 获取用户的角色
     *
     * @param username
     * @return
     */
    @Select("SELECT r.id as roleId,r.role_name as roleName,r.role_description as roleDescription FROM p_role r LEFT JOIN p_role_user ru on ru.role_id=r.id LEFT JOIN p_user u ON u.id = ru.user_id where u.username = #{username}")
    List<Role> findUserRoles(String username);

    /**
     * 插入一条用户-角色记录
     *
     * @param userId
     * @param roleId
     */
    @Insert("INSERT INTO p_role_user VALUES (#{userId},#{roleId})")
    void insertUserRole(Integer userId, Integer roleId);

    /**
     * 根据userid查找用户和角色
     *
     * @param userId
     * @return
     */
    @Select("select id,role_name,role_description from p_role r LEFT JOIN  p_role_user ru ON r.id = ru.role_id  where ru.user_id = #{userId}")
    @Results(id = "roleUserResultMap", value = {
            @Result(property = "roleId", column = "id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleDescription", column = "role_description")
    })
    List<Role> findByUserId(Integer userId);
}
