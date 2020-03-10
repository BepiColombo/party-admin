package com.tck.party.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -4301372929899807565L;

    private Integer roleId;

    private Integer menuId;
}
