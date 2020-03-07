package com.tck.party.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserManageParam implements Serializable {

    private static final long serialVersionUID = 4828517179853188968L;

    private Integer isValid;

    private Integer roleId;
}
