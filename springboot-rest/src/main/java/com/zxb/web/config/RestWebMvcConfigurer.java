package com.zxb.web.config;

import com.zxb.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * REST {@link WebMvcConfigurer} 实现
 * @author Mr.zxb
 * @date 2019-08-23 17:09
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 不建议添加到converters的末尾，会导致优先级的问题，不会生效
//        converters.add(new PropertiesHttpMessageConverter());
        converters.add(0, new PropertiesHttpMessageConverter());
    }
}
