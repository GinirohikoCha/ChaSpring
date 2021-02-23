package com.github.ginirohikocha.spring.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.ginirohikocha.spring.entity.Test;
import com.github.ginirohikocha.spring.service.ITestService;
import com.github.ginirohikocha.spring.vo.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/21 20:50
 */
@RestController()
@RequestMapping("test")
public class TestController extends BaseController<Test> {

    public TestController(ITestService testService) {
        super(testService);
        // 添加Listener来启用对应路由
        setOnQueryListener(new OnQueryListener<Test>() {
            @Override
            public QueryWrapper<Test> onWrapper(Map<String, String[]> params, QueryWrapper<Test> queryWrapper) {
                return null;
            }

            @Override
            public List<Test> onList(List<Test> tests) {
                return null;
            }

            @Override
            public Json onReturn(Json json) {
                return null;
            }

            @Override
            public Json onError(Exception exception, Json json) {
                return null;
            }
        });
        setOnCreateListener(new OnCreateListener<Test>() {
            @Override
            public String onRequest(String requestBody) {
                return null;
            }

            @Override
            public Test onToEntity(Test test) {
                return null;
            }

            @Override
            public Json onReturn(Json json) {
                return null;
            }

            @Override
            public Json onError(Exception exception, Json json) {
                return null;
            }
        });
        setOnUpdateListener(new OnUpdateListener<Test>() {
            @Override
            public String onRequest(String requestBody) {
                return null;
            }

            @Override
            public Test onToEntity(Test test) {
                return null;
            }

            @Override
            public List<Test> onToEntities(List<Test> tests) {
                return null;
            }

            @Override
            public Test onQueryEntity(Map<String, String[]> params, Test test) {
                return null;
            }

            @Override
            public Json onReturn(Json json) {
                return null;
            }

            @Override
            public Json onError(Exception exception, Json json) {
                return null;
            }
        });
        setOnDeleteListener(new OnDeleteListener<Test>() {
            @Override
            public Json onReturn(Json json) {
                return null;
            }

            @Override
            public Json onError(Exception exception, Json json) {
                return null;
            }
        });
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("json")
    public Json json() {
        return Json.succ()
                .msg("Hello Json!")
                .data("Put data here!")
                .put("additional", "Additional data here!");
    }

    @GetMapping("mysql")
    public String mysql() {
        return service.getOne(null).getValue();
    }

    @Override
    public Object responseUnregisteredListener() {
        // 当访问的路由未注册监听器(未启用)时返回失败提示
        return Json.fail();
    }
}
