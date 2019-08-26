package com.zxb.web.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Properties;

/**
 * {@link Properties} {@link HttpMessageConverter} 实现
 * @author Mr.zxb
 * @date 2019-08-23 16:43
 */
public class  PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        // 设置支持的MediaType
        super(new MediaType("text", "properties"));
    }

    /**
     * 写操作
     * @param properties
     * @param type
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // Properties -> String
        // OutputStream -> Writer

        final HttpHeaders headers = outputMessage.getHeaders();
        final MediaType mediaType = headers.getContentType();
        // 获取字符编码
        Charset charset = Optional.ofNullable(mediaType.getCharset())
                .orElse(Charset.forName("UTF-8"));

        // 字节输出流
        final OutputStream outputStream = outputMessage.getBody();
        // 字符输出流
        final Writer wr = new OutputStreamWriter(outputStream, charset);

        // Properties 写入到字符输出流
        properties.store(wr, "From PropertiesHttpMessageConverter writeInternal");
    }

    /**
     * 读操作
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // 字符流-> 字符编码
        // 从请求头Content-Type 解析编码
        final HttpHeaders headers = inputMessage.getHeaders();
        final MediaType mediaType = headers.getContentType();
        // 获取字符编码
        Charset charset = Optional.ofNullable(mediaType.getCharset())
                .orElse(Charset.forName("UTF-8"));

        // 字节流
        final InputStream inputStream = inputMessage.getBody();
        final InputStreamReader reader = new InputStreamReader(inputStream, charset);

        final Properties properties = new Properties();

        // 加载字符流成为properties对象
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }


}
