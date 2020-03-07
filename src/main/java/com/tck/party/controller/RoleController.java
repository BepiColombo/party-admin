package com.tck.party.controller;

import com.tck.party.common.controller.BaseController;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.vo.PartyResponse;
import com.tck.party.entity.Role;
import com.tck.party.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;


    @RequiresPermissions("role:view")
    @GetMapping(value = "getRoleList")
    public PartyResponse findRoleList() {
        List<Role> roles = roleService.findRoleList();
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), roles);
    }
}
