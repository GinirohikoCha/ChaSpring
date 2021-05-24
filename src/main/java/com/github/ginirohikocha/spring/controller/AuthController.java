package com.github.ginirohikocha.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.ginirohikocha.spring.entity.SysUser;
import com.github.ginirohikocha.spring.entity.SysUserAuth;
import com.github.ginirohikocha.spring.service.ISysUserAuthService;
import com.github.ginirohikocha.spring.service.ISysUserService;
import com.github.ginirohikocha.spring.util.SecurityUtil;
import com.github.ginirohikocha.spring.vo.Json;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author GinirohikoCha
 * @since 2021/5/20
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    private final ISysUserService sysUserService;
    private final ISysUserAuthService sysUserAuthService;

    public AuthController(ISysUserService sysUserService, ISysUserAuthService sysUserAuthService) {
        this.sysUserService = sysUserService;
        this.sysUserAuthService = sysUserAuthService;
    }

    @PostMapping("login")
    public Json login(@RequestBody String body) {

        JSONObject json = JSON.parseObject(body);
        String username = json.getString("username");
        String password = json.getString("password");

        if (!StringUtils.hasLength(username) | !StringUtils.hasLength(password)){
            return Json.fail("用户名或密码不能为空");
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));

            return Json.succ().msg("登陆成功");

        } catch (UnknownAccountException | IncorrectCredentialsException unknownAccountException ) {
            return Json.fail("用户名或密码不正确");
        } catch (AuthenticationException authenticationException) {
            return Json.fail("登录失败：" + authenticationException.getMessage());
        }
    }

    @PostMapping("register")
    public Json register(@RequestBody String body) {
        try {
            JSONObject json = JSON.parseObject(body);
            String username = json.getString("username");
            String password = json.getString("password");

            if (!StringUtils.hasLength(username) | !StringUtils.hasLength(password)){
                return Json.fail("用户名或密码不能为空");
            }

            SysUser user = new SysUser();
            user.setNickname(username);
            sysUserService.save(user);
            SysUserAuth userAuth = new SysUserAuth();
            userAuth.setUserId(user.getId());
            userAuth.setIdentifier(username);
            userAuth.setIdentityType("password");
            userAuth.setSalt(SecurityUtil.generateRandomSalt());
            userAuth.setCredential(SecurityUtil.md5(password, userAuth.getSalt()));
            sysUserAuthService.save(userAuth);

            return Json.succ("注册成功");
        } catch (DuplicateKeyException duplicateKeyException) {
            return Json.fail("该用户名已注册");
        } catch (Exception exception) {
            exception.printStackTrace();
            return Json.fail();
        }
    }

    @GetMapping("session")
    public String session() {
        SysUserAuth sysUserAuth = (SysUserAuth) SecurityUtils.getSubject().getPrincipal();
        return sysUserAuth.toString();
    }

}
