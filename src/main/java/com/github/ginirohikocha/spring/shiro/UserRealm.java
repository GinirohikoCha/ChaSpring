package com.github.ginirohikocha.spring.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.ginirohikocha.spring.entity.User;
import com.github.ginirohikocha.spring.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/24 15:06
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    public UserRealm(IUserService userService) {
        this.userService = userService;
    }

    public UserRealm() {
        // md5循环加密3次
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
        String username = (String) authenticationToken.getPrincipal();
        User userDB = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (userDB == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(
                userDB, userDB.getPassword(), ByteSource.Util.bytes(userDB.getSalt()), getName());
    }
}
