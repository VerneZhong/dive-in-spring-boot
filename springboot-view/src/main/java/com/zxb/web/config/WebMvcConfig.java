package com.zxb.web.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
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
     *
     * @return
     */
    @Bean
    public ViewResolver myViewResolver() {
//        InternalResourceViewResolver viewResolver = new CustomizeInternalResourceViewResolver();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        // 设置优先级
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        // 配置ViewResolver的Content-Type
        viewResolver.setContentType("text/xml;charset=UTF-8");
        return viewResolver;
    }

    /**
     * 解决Maven多模块，JSP无法读取的问题，修复SpringBoot获取多maven项目的路径问题
     *
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

    /**
     * 内容协商添加策略
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 配置允许添加策略
        // 允许添加请求参数 ParameterContentNegotiationStrategy 策略
        configurer.favorParameter(true)
                // 允许添加路径扩展名 PathExtensionContentNegotiationStrategy 策略
                .favorPathExtension(true);
    }
}
