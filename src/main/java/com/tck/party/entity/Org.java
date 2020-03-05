package com.tck.party.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 组织实体类
 */
@Data
public class Org implements Serializable {
    private static final long serialVersionUID = 6567756687287122282L;

    private Integer orgId;
    private String orgAddress;
    private String orgName;
    private Integer parentId;
}
