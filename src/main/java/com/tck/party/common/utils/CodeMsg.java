package com.tck.party.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CodeMsg {
    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");

    public static CodeMsg AUTH_ERR = new CodeMsg(400000, "令牌为空或已失效");
    public static CodeMsg UNAUTHORIZED = new CodeMsg(400001, "unauthorized");
    public static CodeMsg TOKEN_EXPIRED = new CodeMsg(400002, "token is expired");
    public static CodeMsg TOKEN_INVALID = new CodeMsg(400003, "token is invalid");
    public static CodeMsg AUTH_DENY = new CodeMsg(400004, "权限不足");

    public static CodeMsg BAD_REQUEST = new CodeMsg(500000, "bad_request");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500001, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500002, "参数异常：%s");

    public static CodeMsg USER_EXIST = new CodeMsg(500100, "用户名已注册");


    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
