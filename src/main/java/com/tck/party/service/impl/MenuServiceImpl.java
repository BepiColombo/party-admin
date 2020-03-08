package com.tck.party.service.impl;

import com.tck.party.entity.Menu;
import com.tck.party.mapper.MenuMapper;
import com.tck.party.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    /**
     * 获取用户所对应的menu
     *
     * @param username
     * @return
     */
    public List<Menu> findUserMenus(String username) {
        List<Menu> menus = menuMapper.findUserMenus(username);
        return menus;
    }

    /**
     * 获取所有的menu
     *
     * @return
     */
    public List<Menu> findMenuList() {
        List<Menu> menus = menuMapper.findMenuList();
        return menus;
    }

    /**
     * 更新菜单信息
     *
     * @param menu
     * @return
     */
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    public int deleteMenu(Integer menuId) {
        return menuMapper.deleteMenu(menuId);
    }

    /**
     * 插入一条菜单记录
     * @param menu
     * @return
     */
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }
}
