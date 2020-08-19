package com.zxb.web.template.engine;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Thymeleaf 模板引擎引导类
 * @author Mr.zxb
 * @date 2019-08-20 22:39:38
 */
public class ThymeleafTemplateEngineBootstrap {

    public static void main(String[] args) throws IOException {
        // Thymeleaf与Spring资源整合
        // 构建模板引擎
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // 创建渲染上下文
        Context context = new Context();
        context.setVariable("message", "hello thymeleaf");
        // 模板的内容
        // !!!伪标签
//        String content = "<p th:text=\"${message}\">!!!</p>";

        // 读取内容从classpath:/templates/thymeleaf/hello-world.html
        // ResourceLoader
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过classpath:/templates/thymeleaf/hello-world.html Resource
        Resource resource = resourceLoader.getResource("classpath:/templates/thymeleaf/hello-world.html");
        File file = resource.getFile();

        // 文件流
        FileInputStream inputStream = new FileInputStream(file);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // copy
        IOUtils.copy(inputStream, outputStream);

        // 模板的内容
        String content = outputStream.toString("UTF-8");

        inputStream.close();
        // 渲染（处理）结果
        String result = templateEngine.process(content, context);
        // 输出渲染结果
        System.out.println(result);
    }
}
