package com.zxb.web.external.configuration.bootstrap;

import com.zxb.web.external.configuration.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring XML 配置占位符示例
 * @author Mr.zxb
 * @date 2019-09-05 09:53
 */
public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {

        String[] locations = {"classpath:/META-INF/spring/user-context.xml","classpath:/META-INF/spring/spring-context.xml"};

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);

        User user = context.getBean("user", User.class);

        System.out.println(user);

        context.close();
    }
}
