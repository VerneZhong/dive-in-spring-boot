package com.zxb.web.external.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySources;

/**
 * 扩展 {@link PropertySources} 引导类
 * @author Mr.zxb
 * @date 2019-09-10 15:09
 */
@EnableAutoConfiguration
@Configuration
@PropertySource(name = "from classpath:default.properties", value = "classpath:META-INF/default.properties")
public class ExtendPropertySourcesBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                .web(WebApplicationType.NONE)
                // Default properties
                .properties("user.id=99")
                // Command line arguments
                .run(of("--user.id=88"));

        // 获取Environment对象
        final ConfigurableEnvironment environment = context.getEnvironment();

        System.out.println("userId: " + environment.getProperty("user.id"));

        environment.getPropertySources()
                .forEach(propertySource -> System.out.printf("PropertySource[名称：%s]：%s\n", propertySource.getName(), propertySource));

        context.close();
    }

    private static <T> T[] of(T... args) {
        return args;
    }
}
