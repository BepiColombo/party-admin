package com.tck.party.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -2772362593435797763L;
    private Integer roleId;

    @NotBlank(message = "角色名称不能为空")
    private  String roleName;

    private String roleDescription;
}
