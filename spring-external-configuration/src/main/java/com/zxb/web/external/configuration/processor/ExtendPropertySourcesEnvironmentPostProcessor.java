package com.zxb.web.external.configuration.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} {@link EnvironmentPostProcessor} 实现
 * @author Mr.zxb
 * @date 2019-09-11 09:22
 */
public class ExtendPropertySourcesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(16);
        // 1.from-ApplicationEnvironmentPreparedEvent 1
        // 2.from-environmentPrepared 2
        // 3.from-EnvironmentPostProcessor 3
        // application.properties   1
        // META-INF/default.properties  7
        source.put("user.id", 3);
        MapPropertySource mapPropertySource = new MapPropertySource("from-EnvironmentPostProcessor", source);
        propertySources.addFirst(mapPropertySource);
    }

    /**
     * 设置高于读取application.properties配置文件的优先级
     * @return
     */
    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }
}
