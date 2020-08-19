package com.zxb.web.external.configuration.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} {@link ApplicationContextInitializer} 的实现
 * @author Mr.zxb
 * @date 2019-09-11 11:10
 */
public class ExtendPropertySourcesApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(16);
        // 1.from-ApplicationContextInitializer : 0
        // 2.from-ApplicationEnvironmentPreparedEvent 1
        // 3.from-environmentPrepared 2
        // 4.from-EnvironmentPostProcessor 3
        // application.properties   1
        // META-INF/default.properties  7
        source.put("user.id", 0);
        // 排在最后的覆盖前面的
        propertySources.addFirst(new MapPropertySource("from-ApplicationContextInitializer", source));
    }
}
