package com.zxb.web.external.configuration.bootstrap;

import com.zxb.web.external.configuration.entity.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringBoot 加载XML文件配置示例
 * @author Mr.zxb
 * @date 2019-09-05 10:25
 */
// 加载Spring上下文xml文件
@ImportResource("META-INF/spring/user-context.xml")
@EnableAutoConfiguration
public class XmlPlaceholderExternalizedConfigurationBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean("user", User.class);

        // 系统属性的优先级高于，application.properties的配置属性
        System.out.println("System user name: " + System.getProperty("user.name"));
        System.out.println("Application user name: " + user);

        context.close();
    }
}
