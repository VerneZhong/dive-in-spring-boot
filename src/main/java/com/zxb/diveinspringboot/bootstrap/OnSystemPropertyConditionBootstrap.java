package com.zxb.diveinspringboot.bootstrap;

import com.zxb.diveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 系统属性条件判断引导类
 * @author Mr.zxb
 * @date 2019-08-15 09:30
 */
public class OnSystemPropertyConditionBootstrap {

    /**
     * Bean装配的前置判断
     * @return
     */
    @Bean
    @ConditionalOnSystemProperty(name = "user.name", value = "DELL")
    public String helloWorld() {
        return "Hello World zxb";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OnSystemPropertyConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 通过类型名称获取bean
        String hello = context.getBean("helloWorld", String.class);

        System.out.println("hello = " + hello);

        // 关闭上下文
        context.close();
    }
}
