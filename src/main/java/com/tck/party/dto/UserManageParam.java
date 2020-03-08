package com.tck.party.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户管理中的（修改用户）
 */
@Data
public class UserManageParam implements Serializable {

    private static final long serialVersionUID = 4828517179853188968L;

    @NotBlank(message = "用户id不能为空")
    private Integer userId;

    private Integer isValid;

    private Integer roleId;
}
