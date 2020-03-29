package com.tck.party.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tck.party.common.controller.BaseController;
import com.tck.party.common.base.ActiveUser;
import com.tck.party.common.config.PartyConstant;
import com.tck.party.common.service.RedisService;
import com.tck.party.common.utils.*;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.service.dto.UserDto;
import com.tck.party.vo.LoginParam;
import com.tck.party.entity.Menu;
import com.tck.party.entity.User;
import com.tck.party.service.MenuService;
import com.tck.party.service.RoleService;
import com.tck.party.service.UserService;
import com.tck.party.shiro.JWTToken;
import com.tck.party.shiro.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Validated
@RestController
@RequestMapping("")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;

    @Autowired
    RedisService redisService;

    @Autowired
    private ObjectMapper mapper;
    private User user;

    /**
     * 登录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "login")
    public PartyResponse login(@RequestBody LoginParam loginParam,
                               HttpServletRequest request) throws Exception {

        String username = loginParam.getUsername();
        String password = loginParam.getPassword();

        User user = userService.findUserByUserName(username);

        if (user == null)
            return new PartyResponse(CodeMsg.USER_NOT_EXIST.getCode(), CodeMsg.USER_NOT_EXIST.getMsg(), "");
        if (!StringUtils.equals(user.getPassword(), password))
            return new PartyResponse(CodeMsg.USER_PWD_ERR.getCode(), CodeMsg.USER_PWD_ERR.getMsg(), "");

        JWTToken jwtToken = this.generateToken(user);

        //存入redis
        this.saveTokenToRedis(user, jwtToken, request);

        Map<String, Object> result = new HashMap<>();
        result.put("token", jwtToken.getToken());
        logger.info(user.getUsername() + "login");
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "登录成功", result);
    }

    /**
     * 注销登录
     *
     * @param token
     * @return
     */
    @PostMapping(value = "logout")
    public PartyResponse logout(@RequestHeader(name = "Authorization") String token) {
        System.out.println(token);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "注销成功", "");
    }

    /**
     * 获取用户详情信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "getUserInfo")
    public PartyResponse getUserInfo(@RequestHeader("Authorization") String token) throws Exception {
        String decryptToken = PartyUtils.decryptToken(token);
        String username = JWTUtil.getUsername(decryptToken);
        UserDto user = userService.findUserDetail(username);
        List<Menu> menus = menuService.findUserMenus(username);
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", user);
        result.put("menus", menus);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "", result);
    }

    /**
     * 更新个人资料
     * @param user
     * @return
     */
    @PostMapping(value = "updateProfile")
    public PartyResponse updateProfile(@RequestBody User user) {
        int res = userService.updateUser(user);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "更新成功", "");
        } else {
            return new PartyResponse(CodeMsg.UPDATE_ACTION_FAIL.getCode(), CodeMsg.UPDATE_ACTION_FAIL.getMsg(), "");
        }
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    private JWTToken generateToken(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //生成token（进行了加密）
        String token = PartyUtils.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(PartyConstant.JWT_TIMEOUT);
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

//        System.out.println("LoginController generateToken: "+expireTimeStr);

        return jwtToken;
    }

    /**
     * 存入redis用户的token
     *
     * @param user
     * @param token
     * @param request
     * @return
     * @throws Exception
     */
    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
//        activeUser.setLoginAddress(AddressUtil.getCityInfo(ip));

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(PartyConstant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(PartyConstant.TOKEN_CACHE_PREFIX + token.getToken() + "." + ip, token.getToken(), PartyConstant.JWT_TIMEOUT * 1000);

        return activeUser.getId();
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "regist")
    public PartyResponse reigist(@RequestBody @Valid User user, HttpServletRequest request) throws Exception {
        User find_user = userService.findUserByUserName(user.getUsername());
        if (find_user != null) {
            //判断用户是否已存在
            return new PartyResponse(CodeMsg.USER_EXIST.getCode(), CodeMsg.USER_EXIST.getMsg(), "");
        }
        //插入数据库
        userService.insertUser(user);
        //处理token
        JWTToken jwtToken = this.generateToken(user);
        this.saveTokenToRedis(user, jwtToken, request);

        //生成对应的角色及菜单集合
        String username = user.getUsername();
        List<Menu> menus = menuService.findUserMenus(username);
        Set<String> roles = userService.findUserRoles(username);
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", user);
        result.put("menus", menus);
        result.put("roles", roles);
        result.put("token", jwtToken.getToken());
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "注册成功", result);
    }
}
