package com.github.ginirohikocha.spring.controller;

import com.github.ginirohikocha.spring.service.ITestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/21 20:50
 */
@RestController()
@RequestMapping("/test")
public class TestController {

    private final ITestService testService;

    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/mysql")
    public String mysql() {
        return testService.getOne(null).getValue();
    }
}
