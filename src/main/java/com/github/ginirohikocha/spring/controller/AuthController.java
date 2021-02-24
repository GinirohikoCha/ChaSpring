package com.github.ginirohikocha.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.ginirohikocha.spring.entity.User;
import com.github.ginirohikocha.spring.service.IUserService;
import com.github.ginirohikocha.spring.util.SecurityUtil;
import com.github.ginirohikocha.spring.util.UUIDUtil;
import com.github.ginirohikocha.spring.vo.Json;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/24 17:41
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("401")
    public Json page401() {
        throw new UnauthenticatedException();
    }

    @RequestMapping("403")
    public Json page403() {
        throw new UnauthorizedException();
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

            return Json.succ();

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

            User user = new User();
            user.setId(UUIDUtil.generateRandomUUID());
            user.setUsername(username);
            user.setSalt(SecurityUtil.generateRandomSalt());
            user.setPassword(SecurityUtil.MD5(password, user.getSalt()));

            userService.save(user);

            return Json.succ("注册成功");
        } catch (DuplicateKeyException duplicateKeyException) {
            return Json.fail("该用户名已注册");
        } catch (Exception exception) {
            exception.printStackTrace();
            return Json.fail();
        }
    }
}
