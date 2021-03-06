package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import com.tck.party.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> findUserMenus(String username);


    /**
     * 通过roleId获取菜单
     * @param roleId
     * @return
     */
    List<RoleMenu> findMenusByRoleId(Integer roleId);

    /**
     * 获取所有的menu
     * @return
     */
    List<Menu> findMenuList();

    /**
     * 更新菜单信息
     */
    int updateMenu(Menu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    int deleteMenu(Integer menuId);

    /**
     * 插入一条菜单记录
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);
}
