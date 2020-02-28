package com.tck.party.common.exception;

/**
 * 系统内部异常
 */
public class PartyException extends Exception {

    private static final long serialVersionUID = -994962710559017255L;

    public PartyException(String message) {
        super(message);
    }
}
