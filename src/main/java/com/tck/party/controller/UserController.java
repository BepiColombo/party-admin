package com.tck.party.controller;


import com.tck.party.common.controller.BaseController;
import com.tck.party.common.vo.PageResult;
import com.tck.party.dto.UserManageParam;
import com.tck.party.entity.User;
import com.tck.party.dto.UserQueryParam;
import com.tck.party.service.impl.RoleServiceImpl;
import com.tck.party.service.impl.UserServiceImpl;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.vo.PartyResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private String message;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    /**
     * 获取所有的用户
     *
     * @return
     */
    @RequiresPermissions("user:view")
    @PostMapping(value = "getUserList")
    public PartyResponse<PageResult<User>> findAllUser(@RequestBody @Valid UserQueryParam userQueryParam) {
        PageResult<User> users = userService.findUsers(userQueryParam);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), users);
    }


    /**
     * 更新用户
     *
     * @param userManageParam
     * @return
     */
    @RequiresPermissions("user:update")
    @PostMapping(value = "updateUserByManager")
    @Transactional
    public PartyResponse updateUserByManager(@RequestBody UserManageParam userManageParam) {
        int user_res = userService.updateUserByManager(userManageParam);
        int role_res = roleService.updateUserRole(userManageParam.getUserId(), userManageParam.getRoleId());
        if (user_res == 1 && role_res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.UPDATE_ACTION_SUCCESS.getMsg());
        } else {
            message = "更新用户失败";
            logger.error(message + ":" + userManageParam.getUserId());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new PartyResponse(CodeMsg.UPDATE_ACTION_FAIL.getCode(), CodeMsg.UPDATE_ACTION_FAIL.getMsg());
        }


    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequiresPermissions("user:delete")
    @PostMapping(value = "deleteUser")
    public PartyResponse deleteUser(@RequestParam("userId") Integer userId) {
        int res = userService.deleteUserById(userId);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.DEL_ACTION_SUCCESS.getMsg());
        } else {
            message = "删除用户失败";
            logger.error(message + " : " + userId);
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), CodeMsg.DEL_ACTION_FAIL.getMsg());
        }

    }
}
