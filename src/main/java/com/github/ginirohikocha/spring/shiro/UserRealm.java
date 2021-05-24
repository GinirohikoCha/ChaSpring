package com.github.ginirohikocha.spring.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.ginirohikocha.spring.entity.SysUserAuth;
import com.github.ginirohikocha.spring.service.ISysUserAuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author GinirohikoCha
 * @since 2021/5/20
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private ISysUserAuthService userAuthService;

    @Autowired
    public void setUserAuthService(ISysUserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    public UserRealm() {
        HashedCredentialsMatcher passwordMatcher = new HashedCredentialsMatcher("md5");
        passwordMatcher.setHashIterations(3);
        this.setCredentialsMatcher(passwordMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String identifier = (String) authenticationToken.getPrincipal();
        SysUserAuth userAuth = userAuthService.getOne(new QueryWrapper<SysUserAuth>().eq("identifier", identifier));
        if (userAuth == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(
                userAuth, userAuth.getCredential(), ByteSource.Util.bytes(userAuth.getSalt()), getName());
    }

}
