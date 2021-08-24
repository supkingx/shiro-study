package com.supkingx.realm;

import com.supkingx.service.SecurityService;
import com.supkingx.service.SecurityServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/24
 */
public class DefinitionRealm extends AuthorizingRealm {

    private SecurityService securityService = new SecurityServiceImpl();

    /**
     * 鉴权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = (String) authenticationToken.getPrincipal();
        String password = securityService.findPasswordByUserName(loginName);
        if (password == null || "".equals(password)) {
            throw new UnknownAccountException("账户不存在");
        }
        // 账户密码必须匹配
        return new SimpleAuthenticationInfo(loginName, password, getName());
    }
}
