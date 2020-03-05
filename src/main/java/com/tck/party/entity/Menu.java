package com.tck.party.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单实体类
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 11804043480619174L;

    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String perms;

    private String url;
}
