package com.tck.party.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleParam {
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @NotBlank(message = "角色描述不能为空")
    private String roleDescription;

    @NotBlank(message = "菜单id不能为空集合")
    private String menuIds;
}
