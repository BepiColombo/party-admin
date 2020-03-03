package com.tck.party.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    private Integer menuId;
    private Integer parentId;
    private String menuName;
    private String perms;
    private String url;
}
