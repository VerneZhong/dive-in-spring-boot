package com.zxb.spring.boot.web.servlet.support;

import com.zxb.spring.boot.web.servlet.SpringBootServletBootstrap;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * {@link SpringBootServletInitializer} 的自定义实现
 * @author Mr.zxb
 * @date 2019-08-28 14:47
 */
public class DefaultSpringBootServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(SpringBootServletBootstrap.class);
        return builder;
    }
}
