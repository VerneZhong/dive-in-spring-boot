package com.zxb.web.boostrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Web Mvc 引导类
 * @author Mr.zxb
 * @date 2019-08-20 17:30
 */
@SpringBootApplication(scanBasePackages = "com.zxb.web")
public class SpringBootWebMvcBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcBootstrap.class, args);
    }
}
