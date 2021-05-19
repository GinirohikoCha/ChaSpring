package com.github.ginirohikocha.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GinirohikoCha
 * @since 2021/5/19
 */
@SpringBootApplication
@MapperScan("com.github.ginirohikocha.spring.mapper")
public class ChaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaSpringApplication.class, args);
    }

}
