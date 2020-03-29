package com.tck.party.controller;

import com.tck.party.common.controller.BaseController;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.RoleMenu;
import com.tck.party.service.MenuService;
import com.tck.party.service.RoleService;
import com.tck.party.vo.RoleParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
     * 批量更新角色对应的菜单
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
     * 添加角色
     *
     * @param roleParam
     * @return
     */
    @RequiresPermissions("role:add")
    @PostMapping(value = "addRole")
    public PartyResponse addRole(@RequestBody RoleParam roleParam) throws Exception {


        Role role = new Role();
        role.setRoleName(roleParam.getRoleName());
        role.setRoleDescription(roleParam.getRoleDescription());
        int role_res = roleService.insertRole(role);

        //菜单id处理成list
        String[] strs = roleParam.getMenuIds().split(",");
        List<String> _menuIds = Arrays.asList(strs);
        List<Integer> menuIds = _menuIds.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        if (role_res != 0) {
            int role_menu_res = roleService.insertRoleMenuBatch(role_res, menuIds);
            if (role_menu_res != 0) {
                return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), "添加成功");
            } else {
                return new PartyResponse(CodeMsg.ADD_ACTION_FAIL.getCode(), CodeMsg.ADD_ACTION_FAIL.getMsg(), "");
            }
        } else {
            return new PartyResponse(CodeMsg.ADD_ACTION_FAIL.getCode(), CodeMsg.ADD_ACTION_FAIL.getMsg(), "");
        }

    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @RequiresPermissions("role:update")
    @PostMapping(value = "updateRole")
    public PartyResponse updateRole(@RequestBody Role role) {
        int res = roleService.updateRole(role);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "更新成功", "");
        } else {
            return new PartyResponse(CodeMsg.UPDATE_ACTION_FAIL.getCode(), CodeMsg.UPDATE_ACTION_FAIL.getMsg(), "");
        }
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequiresPermissions("role:delete")
    @PostMapping(value = "deleteRole")
    public PartyResponse deleteRole(@RequestBody Map<String, Integer> data) {
        int res = roleService.deleteRole(data.get("roleId"));
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "删除成功", "");
        } else {
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), CodeMsg.DEL_ACTION_FAIL.getMsg(), "");
        }
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
