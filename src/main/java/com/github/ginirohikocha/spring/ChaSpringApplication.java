package com.github.ginirohikocha.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/21 00:00
 */

@SpringBootApplication
@MapperScan("com.github.ginirohikocha.spring.mapper")
public class ChaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaSpringApplication.class, args);
    }

}
