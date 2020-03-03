package com.tck.party.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tck.party.common.controller.BaseController;
import com.tck.party.common.domain.ActiveUser;
import com.tck.party.common.domain.PartyConstant;
import com.tck.party.common.exception.PartyException;
import com.tck.party.common.service.RedisService;
import com.tck.party.common.utils.*;
import com.tck.party.entity.User;
import com.tck.party.service.UserService;
import com.tck.party.shiro.JWTToken;
import com.tck.party.shiro.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Validated
@RestController
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping(value = "login")
    public PartyResponse login(@NotBlank(message = "{required}") String username,
                               @NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
        User user = userService.findUserByUserName(username);


        final String errorMessage = "用户名或密码错误";
        if (user == null)
            throw new PartyException(errorMessage);
        if (!StringUtils.equals(user.getPassword(), password))
            throw new PartyException(errorMessage);

        String token = PartyUtils.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(PartyConstant.JWT_TIMEOUT);
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
//        System.out.println(jwtToken);

        this.saveTokenToRedis(user, jwtToken, request);
//        user.setUserId(userId);

        Map<String, Object> result = this.generateUserInfo(jwtToken, user);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "登录成功", result);
    }

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
        this.redisService.set(PartyConstant.TOKEN_CACHE_PREFIX + token.getToken() + "." + ip, token.getToken(), PartyConstant.JWT_TIMEOUT);

        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息
     *
     * @param token token
     * @param user  用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
        String username = user.getUsername();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());

        Set<String> roles = userService.findUserRoles(username);
        userInfo.put("roles", roles);

        Set<String> permissions = userService.findUserPermissions(username);
        userInfo.put("permissions", permissions);

        userInfo.put("user", user);
        return userInfo;
    }

}
