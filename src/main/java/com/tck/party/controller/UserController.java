package com.tck.party.controller;


import com.tck.party.common.controller.BaseController;
import com.tck.party.common.domain.BasePageQuery;
import com.tck.party.common.vo.PageResult;
import com.tck.party.entity.User;
import com.tck.party.query.UserQuery;
import com.tck.party.service.impl.UserServiceImpl;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.vo.PartyResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取所有的用户
     *
     * @return
     */
    @RequiresPermissions("user:view")
    @PostMapping(value = "getUserList")
    public PartyResponse<PageResult<User>> findAllUser(@RequestBody @Valid UserQuery userQuery) {
        PageResult<User> users = userService.findUsers(userQuery);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), users);
    }


    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequiresPermissions("user:update")
    @PostMapping(value = "updateUser")
    public PartyResponse updateUser(@RequestBody @Valid User user){
        int res = userService.updateUser(user);
        if(res==1){
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.UPDATE_ACTION_SUCCESS.getMsg());
        }else{
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
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), CodeMsg.DEL_ACTION_FAIL.getMsg());
        }
    }
}
