package com.zxb.diveinspringboot.bootstrap;

import com.zxb.diveinspringboot.annotation.EnableHelloWorld;
import com.zxb.diveinspringboot.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 仓储的引导类
 * @author Mr.zxb
 * @date 2019-08-14 14:11
 */
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 获取 helloWorld Bean
        String  result = context.getBean("helloWorld", String.class);

        System.out.println("result = " + result);

        // 关闭上下文
        context.close();
    }
}
