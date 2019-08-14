package com.zxb.diveinspringboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//传统servlet应用
//@ServletComponentScan(basePackages = "com.zxb.diveinspringboot.web.servlet")
public class DiveInSpringBootApplication {

	public static void main(String[] args) {

		// Fluent API
		new SpringApplicationBuilder(DiveInSpringBootApplication.class)
				// 应用程序不以web应用运行
				.web(WebApplicationType.REACTIVE)
				.run(args);
		// 两种写法等价的
//		SpringApplication.run(DiveInSpringBootApplication.class, args);
	}

}
