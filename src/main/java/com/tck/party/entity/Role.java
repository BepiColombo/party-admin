package com.tck.party.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -2772362593435797763L;
    private Integer roleId;

    private  String roleName;

    private String roleDescription;
}
