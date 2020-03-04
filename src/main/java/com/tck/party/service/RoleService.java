package com.tck.party.service;

import com.tck.party.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户的角色
     * @param username
     * @return
     */
    List<Role> findUserRoles(String username);
}
