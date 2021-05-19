package com.github.ginirohikocha.spring.controller;

import com.github.ginirohikocha.spring.vo.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GinirohikoCha
 * @since 2021/5/19
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("json")
    public Json json() {
        return Json.succ()
                .msg("Hello Json!")
                .data("Put data here!")
                .put("no", "Extra data here!");
    }

}
