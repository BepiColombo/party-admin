package com.tck.party.common.domain;

public class PartyConstant {
    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "party.cache.token.";

    // token有效时间 默认15天
    public static final long JWT_TIMEOUT = 1296000L;

    // 后端免认证接口 url
    public static final String ANNO_URL = "/login,/logout/**,/regist";

    // 存储在线用户的 zset前缀
    public static final String ACTIVE_USERS_ZSET_PREFIX = "party.user.active";
}
