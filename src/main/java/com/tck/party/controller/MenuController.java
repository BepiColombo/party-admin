package com.tck.party.controller;


import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.entity.Menu;
import com.tck.party.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequiresPermissions("menu:view")
    @GetMapping(value = "getMenuList")
    public PartyResponse getMenuList() {
        List<Menu> menus = menuService.findMenuList();
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), menus);
    }
}
