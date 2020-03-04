package com.tck.party.mapper;

import com.tck.party.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 获取用户的角色
     * @param username
     * @return
     */
    @Select("SELECT r.id as roleId,r.role_name as roleName,r.role_description as roleDescription FROM p_role r LEFT JOIN p_role_user ru on ru.role_id=r.id LEFT JOIN p_user u ON u.id = ru.user_id where u.username = #{username}")
    List<Role> findUserRoles(String username);
}
