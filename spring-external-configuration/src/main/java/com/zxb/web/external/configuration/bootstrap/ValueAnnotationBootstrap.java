package com.zxb.web.external.configuration.bootstrap;

import com.zxb.web.external.configuration.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Value 注解示例
 * @author Mr.zxb
 * @date 2019-09-05 10:44
 */
@EnableAutoConfiguration
public class ValueAnnotationBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean("user", User.class);

        // 系统属性的优先级高于，application.properties的配置属性
        System.out.println("Application user name: " + user);

        context.close();

    }

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        return new User(id, name);
    }
}
