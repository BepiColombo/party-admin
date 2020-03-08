package com.tck.party.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户管理中的（修改用户）
 */
@Data
public class UserManageParam implements Serializable {

    private static final long serialVersionUID = 4828517179853188968L;

    private Integer isValid;

    private Integer roleId;
}
