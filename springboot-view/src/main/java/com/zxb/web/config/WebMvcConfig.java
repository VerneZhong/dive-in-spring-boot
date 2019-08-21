package com.zxb.web.config;

import com.zxb.web.servlet.view.CustomizeInternalResourceViewResolver;
import org.apache.catalina.Context;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

/**
 * @author Mr.zxb
 * @date 2019-08-21 10:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置Jsp 视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new CustomizeInternalResourceViewResolver();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 6);
        return viewResolver;
    }

    /**
     * 解决Maven多模块，JSP无法读取的问题，修复SpringBoot获取多maven项目的路径问题
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return factory -> factory.addContextCustomizers(context -> {
            String relativePath = "springboot-view/src/main/webapp";
            // 相对于 user.dir = D:\learnWorkSpace\dive-in-spring-boot
            final File docBaseFile = new File(relativePath);
            // 路径是否存在
            if (docBaseFile.exists()) {
                context.setDocBase(docBaseFile.getAbsolutePath());
            }
        });
    }
}