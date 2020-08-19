package com.zxb.web.config;

import com.zxb.web.http.converter.properties.PropertiesHttpMessageConverter;
import com.zxb.web.method.suppot.PropertiesHandlerMethodArgumentResolver;
import com.zxb.web.method.suppot.PropertiesHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 替换 @RequestBody 在 Controller 类里的自定义实现
 * REST {@link WebMvcConfigurer} 实现
 * @author Mr.zxb
 * @date 2019-08-23 17:09
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

//    @PostConstruct
    public void init() {
        // 获取当前 RequestMappingHandlerAdapter 所有的 Resolver 对象
        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();

        List<HandlerMethodArgumentResolver> modifiableResolvers = new ArrayList<>(resolvers.size() + 1);

        // 添加 PropertiesHandlerMethodArgumentResolver 到首位
        modifiableResolvers.add(new PropertiesHandlerMethodArgumentResolver());
        // 添加已注册的 Resolver 对象集合
        modifiableResolvers.addAll(resolvers);

        // 重新设置 Resolver 集合
        requestMappingHandlerAdapter.setArgumentResolvers(modifiableResolvers);

        // 重新设置 ReturnValueHandler 集合
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> modifiableHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        modifiableHandlers.add(new PropertiesHandlerMethodReturnValueHandler());
        modifiableHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(modifiableHandlers);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 不建议添加到converters的末尾，会导致优先级的问题，不会生效
//        converters.add(new PropertiesHttpMessageConverter());
//        converters.add(0, new PropertiesHttpMessageConverter());
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        // 添加 PropertiesHandlerMethodArgumentResolver 到首位
//        // 添加自定义HandlerMethodArgumentResolver， 优先级低于内建 HandlerMethodArgumentResolver
//        resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
//    }

    /**
     * 添加跨域操作的第二种方式
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
