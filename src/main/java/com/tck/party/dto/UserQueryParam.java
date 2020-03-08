package com.tck.party.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryParam extends BasePageQuery implements Serializable  {
    private static final long serialVersionUID = 7031744276458365785L;

    //模糊搜索关键字
    private String keyword;

    //角色类型 对应的是roleId
    private Integer roleType;

    //注册日期查询的开始日期
    private String startTime;

    //注册日期查询的结束日期
    private String  endTime;
}
