package com.tck.party.controller;

import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.utils.PartyResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ShiroException.class)
    public PartyResponse handle401(ShiroException e) {
        return new PartyResponse(CodeMsg.AUTH_ERR.getCode(), e.getMessage());
    }

    //
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthenticationException.class)
    public PartyResponse handleAuthenticationException(AuthenticationException e) {
        System.out.println(e.getMessage());
        return new PartyResponse(CodeMsg.UNAUTHORIZED.getCode(), e.getMessage());
    }


    // 捕捉其他所有异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public PartyResponse globException(HttpServletRequest request, Throwable ex) {
        return new PartyResponse(CodeMsg.BAD_REQUEST.getCode(), CodeMsg.SERVER_ERROR.getMsg(), null);
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return PartyResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public PartyResponse validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new PartyResponse(CodeMsg.BIND_ERROR.getCode(), message.toString(), null);

    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return PartyResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public PartyResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new PartyResponse(CodeMsg.BIND_ERROR.getCode(), message.toString(), null);
    }


    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.OK)
    public PartyResponse handleUnauthorizedException(Exception e) {
        return new PartyResponse(CodeMsg.AUTH_DENY.getCode(), "权限不足", null);
    }
}
