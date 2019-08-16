package com.zxb.springapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link org.springframework.boot.SpringApplication} 引导类
 *
 * @author Mr.zxb
 * @date 2019-08-15 15:06
 */
//@SpringBootApplication
public class SpringApplicationBootstrap {

    public static void main(String[] args) {

        // 配置源
//        SpringApplication.run(ApplicationConfiguration.class, args);

        // 多种源替代
        // 初始化SpringApplication推断Web应用类型
        // 推断引导类（Main class）
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(Stream.of(ApplicationConfiguration.class.getName())
                .collect(Collectors.toSet()));
//        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);

        System.out.println("Bean: " + context.getBean(ApplicationConfiguration.class));

//        context.close();
    }

    @SpringBootApplication
    public static class ApplicationConfiguration {
    }

}
