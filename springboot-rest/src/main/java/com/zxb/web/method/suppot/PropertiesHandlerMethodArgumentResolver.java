package com.zxb.web.method.suppot;

import com.zxb.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Properties;

/**
 * {@link Properties 类型} {@link HandlerMethodArgumentResolver} 实现
 *
 * @author Mr.zxb
 * @date 2019-08-26 15:27
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // Servlet Request API
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        HttpInputMessage httpInputMessage = new ServletServerHttpRequest(request);

//        // 获取Content-Type 中的媒体类型
//        final MediaType mediaType = MediaType.parseMediaType(request.getContentType());
//        // 获取字符编码，当charset不存在，默认UTF-8
//        Charset charset = Optional.ofNullable(mediaType.getCharset())
//                .orElse(Charset.forName("UTF-8"));
//        // 请求输入字节流
//        InputStream inputStream = request.getInputStream();
//        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
//        Properties properties = new Properties();
//        properties.load(inputStreamReader);

        // 复用 PropertiesHttpMessageConverter
        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();
        return converter.read(null, httpInputMessage);
    }
}
