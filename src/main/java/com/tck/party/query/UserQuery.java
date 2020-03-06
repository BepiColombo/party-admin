package com.tck.party.query;

import com.tck.party.common.domain.BasePageQuery;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

@Data
public class UserQuery extends BasePageQuery implements Serializable  {
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
