package com.zxb.diveinspringboot.autoconfigure.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link EnableAutoConfiguration}
 *
 * @author Mr.zxb
 * @date 2019-08-15 10:29
 */
@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println("helloWorld = " + helloWorld);

        // 关闭上下文
        context.close();
    }
}
