package com.tck.party.common.vo;

import java.io.Serializable;

public class PartyResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    /**
     * 完整的构造函数
     *
     * @param code
     * @param msg
     * @param data
     */
    public PartyResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 没有data需要返回时的构造函数
     *
     * @param code
     * @param msg
     */
    public PartyResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
