package com.tck.party.controller;

import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.utils.ResponseUtils;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseUtils handle401(ShiroException e) {
        return new ResponseUtils(CodeMsg.UNAUTHORIZED.getCode(), e.getMessage());
    }

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseUtils authenticationHandler(ShiroException e) {
//        return new ResponseUtils(CodeMsg.UNAUTHORIZED.getCode(), e.getMessage());
//    }

    // 捕捉其他所有异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseUtils globException(HttpServletRequest request, Throwable ex) {
        return new ResponseUtils(CodeMsg.BAD_REQUEST.getCode(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
