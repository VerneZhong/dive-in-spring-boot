package com.zxb.springapplication;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring应用上下文引导类
 *
 * @author Mr.zxb
 * @date 2019-08-19 10:33
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {

    public static void main(String[] args) {

        final ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        // 创建Spring应用上下文 各类型：
        // 根据准备阶段的推断Web应用类型创建对应的ConfigurableApplicationContext实例
        // Web Reactive：AnnotationConfigReactiveWebServerApplicationContext
        // Web Servlet：AnnotationConfigServletWebServerApplicationContext
        // 非Web：AnnotationConfigApplicationContext
        System.out.println("ConfigurableApplicationContext类型：" + context.getClass().getName());

        // 创建Environment 各类型：
        // 根据准备阶段的推断Web应用类型创建对应的ConfigurableEnvironment实例
        // Web Reactive：StandardReactiveWebEnvironment
        // Web Servlet：StandardServletEnvironment
        // 非Web：StandardEnvironment
        System.out.println("ConfigurableEnvironment类型：" + context.getEnvironment().getClass().getName());

        // 关闭上下文
        context.close();
    }
}
