package com.zxb.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Rest 引导类
 * @author Mr.zxb
 * @date 2019-08-22 17:16
 */
@SpringBootApplication(scanBasePackages = {"com.zxb.web"})
public class SpringBootRestBootstrap {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRestBootstrap.class, args);
    }
}
