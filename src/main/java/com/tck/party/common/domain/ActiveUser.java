package com.tck.party.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tck.party.common.utils.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

/**
 * 在线用户
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveUser {
    private static final long serialVersionUID = 2055229953429884344L;

    // 唯一编号
    private String id = RandomStringUtils.randomAlphanumeric(20);
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN);

}
