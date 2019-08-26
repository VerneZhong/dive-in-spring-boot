package com.zxb.spring.web.servlet.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring Web MVC 自动装配 默认实现 {@link AbstractAnnotationConfigDispatcherServletInitializer }
 *
 * @author Mr.zxb
 * @date 2019-08-20 14:03
 */
@ComponentScan(basePackages = "com.zxb.spring.web.servlet.controller")
public class DefaultAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * web.xml
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * DispatcherServlet
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                getClass() // 返回当前类
        };
    }

    /**
     * 映射/
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
