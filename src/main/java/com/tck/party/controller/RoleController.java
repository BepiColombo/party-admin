package com.tck.party.controller;

import com.tck.party.common.controller.BaseController;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.RoleMenu;
import com.tck.party.service.MenuService;
import com.tck.party.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    /**
     * 获取权限的
     *
     * @return
     */
    @RequiresPermissions("role:view")
    @GetMapping(value = "getRoleList")
    public PartyResponse findRoleList() {
        List<Role> roles = roleService.findRoleList();
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), roles);
    }

    /**
     * 批量更新您角色对应的菜单
     *
     * @param data
     * @return
     */
    @RequiresPermissions("role:menu:batch")
    @PostMapping(value = "batchRoleMenuAction")
    public PartyResponse batchRoleMenuAction(@RequestBody Map<String, Object> data) throws Exception {
        List<Integer> menuIds = (List) data.get("ids");

        Integer roleId = (Integer) data.get("roleId");

        roleService.batchActionRoleMenu(roleId, menuIds);

        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), "");
    }

    /**
     * 查找某一角色的菜单
     *
     * @param roleId
     * @return
     */
    @RequiresPermissions("role:view")
    @GetMapping(value = "getMenusOfRole")
    public PartyResponse getMenusOfRole(@NotBlank(message = "角色不能为空") Integer roleId) {
        List<RoleMenu> menus = menuService.findMenusByRoleId(roleId);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), menus);
    }

    ;
}
