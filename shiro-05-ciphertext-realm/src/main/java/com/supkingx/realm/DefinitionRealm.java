package com.supkingx.realm;

import com.supkingx.service.SecurityService;
import com.supkingx.service.SecurityServiceImpl;
import com.supkingx.utils.DigestsUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Map;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/24
 */
public class DefinitionRealm extends AuthorizingRealm {

    public DefinitionRealm() {
        // 指定密码匹配方式
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(DigestsUtil.SHA1);
        // 指定密码迭代次数
        hashedCredentialsMatcher.setHashIterations(DigestsUtil.ITERATIONS);
        // 使用父层方式使匹配方式生效
        setCredentialsMatcher(hashedCredentialsMatcher);
    }

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
        Map<String, String> map = securityService.findPasswordByUserName(loginName);
        if (map.isEmpty()) {
            throw new UnknownAccountException("账户不存在");
        }
        String salt = map.get("salt");
        String password = map.get("password");
        // 账户密码必须匹配
        return new SimpleAuthenticationInfo(loginName, password, ByteSource.Util.bytes(salt), getName());
    }
}
