package com.tck.party.service.impl;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.RoleMenu;
import com.tck.party.mapper.RoleMapper;
import com.tck.party.service.MenuService;
import com.tck.party.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tck.party.common.utils.PartyUtils.deepCopy;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    MenuService menuService;

    @Autowired
    RoleMapper roleMapper;

    /**
     * 获取所有的角色记录
     *
     * @return
     */
    public List<Role> findRoleList() {
        return roleMapper.findRoleList();
    }


    /**
     * 更新用户角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    public int updateUserRole(Integer userId, Integer roleId) {
        return roleMapper.updateUserRole(userId, roleId);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    public int deleteRole(Integer roleId) {
        return roleMapper.deleteRole(roleId);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 插入一条角色记录
     *
     * @param role
     * @return
     */
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }


    @Transactional(rollbackFor=Exception.class)
    public boolean batchActionRoleMenu(Integer roleId, List<Integer> menuIds) throws Exception {
        //现有的数据库中roleId对应的已有的menuId集合，并转化成List<Integer>的结构
        List<RoleMenu> menus = menuService.findMenusByRoleId(roleId);
        List<Integer> existsMenuIds = menus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

        //将参数中传来的要更新的菜单的id集合深度克隆并保存
        List<Integer> tem = deepCopy(menuIds);
        //1.新增的菜单：参数ids和已存在菜单ids的差集
        menuIds.removeAll(existsMenuIds);
        int insert_row = 0;
        if (menuIds.size() > 0) {
            insert_row = this.insertRoleMenuBatch(roleId, menuIds);
        }
//        System.out.println("插入了" + insert_row);

        //2.要删除的菜单，
        existsMenuIds.removeAll(tem);
        int delete_row = 0;
        if (existsMenuIds.size() > 0) {
            delete_row = this.deleteRoleMenuBatch(roleId, existsMenuIds);
        }

//        System.out.println("删除了" + delete_row);
        return true;
    }

    /**
     * 批量插入role-menu记录
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    public int insertRoleMenuBatch(Integer roleId, List<Integer> menuIds) {
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Integer id : menuIds) {
            RoleMenu menu = new RoleMenu();
            menu.setMenuId(id);
            menu.setRoleId(roleId);
            roleMenus.add(menu);
        }
        return roleMapper.insertRoleMenuBatch(roleMenus);
    }

    /**
     * 批量删除role-menu记录
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    public int deleteRoleMenuBatch(Integer roleId, List<Integer> menuIds) {
        List<Integer> _menuIds = new ArrayList<>();
        for (Integer id : menuIds) {
            _menuIds.add(id);
        }
        Map param = new HashMap();
        param.put("ids", _menuIds);
        param.put("roleId", roleId);
        return roleMapper.deleteRoleMenuBatch(param);
    }
}
