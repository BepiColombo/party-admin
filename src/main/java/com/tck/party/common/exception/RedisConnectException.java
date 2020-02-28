package com.tck.party.common.exception;

/**
 * Redis 连接异常
 */
public class RedisConnectException extends Exception {
    private static final long serialVersionUID = 1639374111871115063L;
    private String message;

    public RedisConnectException(String message) {
        super(message);
    }
}
