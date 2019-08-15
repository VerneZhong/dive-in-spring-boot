package com.zxb.diveinspringboot.autoconfigure.bootstrap;

import com.zxb.diveinspringboot.autoconfigure.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Mr.zxb
 * @date 2019-08-15 09:12
 */
@SpringBootApplication(scanBasePackages = "com.zxb.diveinspringboot.autoconfigure.service")
public class CalculateServiceBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                // 条件装配
                .profiles("Java8")
                .run(args);

        // 获取 helloWorld Bean
        CalculateService calculateService = context.getBean(CalculateService.class);

        System.out.println("result = " + calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // 关闭上下文
        context.close();
    }
}
