package com.zxb.web.servlet.view;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Mr.zxb
 * @date 2019-08-21 10:34
 */
public class CustomizeInternalResourceViewResolver extends InternalResourceViewResolver {

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 6;
    }


}
