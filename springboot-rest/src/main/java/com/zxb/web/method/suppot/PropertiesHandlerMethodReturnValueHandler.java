package com.zxb.web.method.suppot;

import com.zxb.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Properties;

/**
 * 不依赖 @ResponseBody 注解
 * {@link Properties} {@link HandlerMethodReturnValueHandler} 的实现
 *
 * @author Mr.zxb
 * @date 2019-08-26 17:16
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 判断方法的返回类型，是否与 Properties 类型匹配
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

        // 强制转换类型
        Properties properties = (Properties) returnValue;

        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;

        // Servlet Request API
        // 获取Content-Type 中的媒体类型
        final MediaType mediaType = MediaType.parseMediaType(servletWebRequest.getRequest().getContentType());

        // 获取 Servlet Response 对象
        final ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(servletWebRequest.getResponse());
        converter.write(properties, mediaType, outputMessage);

        // 告知 Spring Web MVC 当前请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }

}
