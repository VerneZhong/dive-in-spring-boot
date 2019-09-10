package com.zxb.web.external.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于ApplicationEnvironmentPreparedEvent 扩展外部化配置属性源
 * 扩展 {@link PropertySources} {@link ApplicationListener} 实现，监听 {@link ApplicationEnvironmentPreparedEvent} 事件
 * @author Mr.zxb
 * @date 2019-09-10 16:10
 */
public class ExtendPropertySourcesEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        final ConfigurableEnvironment environment = event.getEnvironment();
        final MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(16);
        // 1.from-ApplicationEnvironmentPreparedEvent 0
        // 2.from-environmentPrepared 9
        // application.properties   1
        // META-INF/default.properties  7
        source.put("user.id", 0);
        MapPropertySource mapPropertySource = new MapPropertySource("from-ApplicationEnvironmentPreparedEvent", source);
        propertySources.addFirst(mapPropertySource);
    }
}
