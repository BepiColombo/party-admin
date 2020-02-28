package com.tck.party.shiro;

import com.tck.party.common.domain.PartyConstant;
import com.tck.party.common.service.RedisService;
import com.tck.party.common.utils.HttpContextUtil;
import com.tck.party.common.utils.IPUtil;
import com.tck.party.entity.User;
import com.tck.party.service.UserService;
import com.tck.party.common.utils.PartyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * `
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

//        // 获取用户角色集
//        Set<String> roleSet = userService.getUserRoles(username);
//        simpleAuthorizationInfo.setRoles(roleSet);
//
//        // 获取用户权限集
//        Set<String> permissionSet = userService.getUserPermissions(username);
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();
        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        String encryptToken = PartyUtils.encryptToken(token);
        String encryptTokenInRedis = null;

        try {
            encryptTokenInRedis = redisService.get(PartyConstant.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
        }
        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis) || encryptTokenInRedis == null) {
            throw new AuthenticationException("token is expired");
        }

        String username = JWTUtil.getUsername(token);
        if (StringUtils.isBlank(username))
            throw new AuthenticationException("token valid");

        // 通过用户名查询用户信息
        User user = userService.findUserByUserName(username);

        if (user == null)
            throw new AuthenticationException("token valid");
        if (!JWTUtil.verify(token, username, user.getPassword()) )
            throw new AuthenticationException("token valid");
        return new SimpleAuthenticationInfo(token, token, "MyShiroRealm");
    }
}
