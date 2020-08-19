package com.zxb.web.external.configuration.bootstrap;

import com.zxb.web.external.configuration.entity.UserProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import java.util.Locale;

/**
 * {@link ConfigurationProperties} 注解引导类
 * 优先级配置：
 * Java System Properties（VM Options）：-Duser.city.post_code=027
 * OS Environment variables：USER_CITY_POST_CODE=020
 * application.properties: user.city.post_code=010
 * @author Mr.zxb
 * @date 2019-09-06 10:45
 */
@EnableAutoConfiguration
@EnableConfigurationProperties
public class ConfigurationPropertiesBootstrap {

    @Bean
    @ConfigurationProperties(prefix = "user")
    // ConditionalOnProperty prefix name 要与 application.propertie 完全一致
    // 在环境变量（OS Environment variables）里面，允许松散绑定 USER_CITY_POST_CODE=020
    @ConditionalOnProperty(prefix = "user.", name = "city.post-code", havingValue = "010")
    public UserProperties user() {
        return new UserProperties();
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConfigurationPropertiesBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        UserProperties user = context.getBean("user", UserProperties.class);
//        UserProperties user = context.getBean(UserProperties.class);

        // 系统属性的优先级高于，application.properties的配置属性
        System.out.println("Application user properties: " + user);

        context.close();
    }
}
