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

    public List<Menu> findUserMenus(String username) {
        List<Menu> menus = menuMapper.findUserMenus(username);
        return menus;
    }
}
