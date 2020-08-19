package com.zxb.diveinspringboot.overview;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//传统servlet应用
//@ServletComponentScan(basePackages = "com.zxb.diveinspringboot.overview.web.servlet")
public class OverviewApplication {

    public static void main(String[] args) {
        // Fluent API
        new SpringApplicationBuilder(OverviewApplication.class)
                // 应用程序不以web应用运行
                .web(WebApplicationType.REACTIVE)
                .run(args);
        // 两种写法等价的
//		SpringApplication.run(DiveInSpringBootApplication.class, args);
    }

}
