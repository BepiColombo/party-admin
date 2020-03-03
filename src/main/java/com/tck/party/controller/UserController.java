package com.tck.party.controller;


import com.github.pagehelper.PageInfo;
import com.tck.party.common.controller.BaseController;
import com.tck.party.common.domain.QueryRequest;
import com.tck.party.entity.User;
import com.tck.party.service.impl.UserServiceImpl;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.common.utils.PartyResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




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
//    @RequiresRoles("super_administrator")
    @RequiresPermissions("user:view")
    @GetMapping(value = "getAllUser")
    public PartyResponse<PageInfo<User>> findAllUser(QueryRequest queryRequest) {
        PageInfo<User> users = userService.findUsers(queryRequest.getPageNum(), queryRequest.getPageSize());
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), users);
    }

//    /**
//     * 添加用户
//     *
//     * @param user
//     * @return
//     */
//    @PostMapping(value = "addUser")
//    public ResponseBean addUser(User user) {
//        int res = userService.addUser(user);
//        if (res == 1) {
//            return ResultGenerator.generateSuccessResult("添加成功");
//        } else {
//            return ResultGenerator.generateFailResult("添加失败");
//        }
//    }

    //    @GetMapping(value = "getUserById")
//    public Result<User> findUserById(@RequestParam("id") Integer id) {
//        User user = userService.findById(id);
//        if (user != null) {
//            return ResultGenerator.generateSuccessResult(user);
//        } else {
//            return ResultGenerator.generateSuccessResult("未找到该用户");
//        }
//    }
//
//
//    @GetMapping(value = "updateUser")
//    public Result<User> updateUser(@RequestBody User user) {
//
//        int res = userService.updateUser(user);
//        if (res == 1) {
//            return ResultGenerator.generateSuccessResult("修改成功");
//        } else {
//            return ResultGenerator.generateFailResult("修改失败");
//
//        }
//    }


//    @PostMapping(value = "addUser")
//    public Result<User> addUser(@RequestBody User user) {
//        int res = userService.insertUser(user);
//        if (res == 1) {
//            return ResultGenerator.generateSuccessResult("添加成功");
//        } else {
//            return ResultGenerator.generateFailResult("添加失败");
//
//        }
//    }
}