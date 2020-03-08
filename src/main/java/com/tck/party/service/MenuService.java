package com.tck.party.service;

import com.tck.party.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 获取用户所拥有的所有菜单选项
     * @param username
     * @return
     */
    List<Menu> findUserMenus(String username);

    /**
     * 获取所有的menu
     * @return
     */
    List<Menu> findMenuList();

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 插入一条菜单记录
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);
}
