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
}
