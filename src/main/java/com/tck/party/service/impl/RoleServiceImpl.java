package com.tck.party.service.impl;

import com.tck.party.entity.Role;
import com.tck.party.mapper.RoleMapper;
import com.tck.party.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> findUserRoles(String username) {
        List<Role> roles = roleMapper.findUserRoles(username);
        return roles;
    }
}
