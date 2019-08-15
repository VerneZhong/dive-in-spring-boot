package com.zxb.diveinspringboot.configuration;

import com.zxb.diveinspringboot.annotation.EnableHelloWorld;
import com.zxb.diveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义自动装配
 * HelloWorldAutoConfiguration
 * 条件判断：user.name == "DELL"
 * 模式注解：@Configuration
 *
 * @author Mr.zxb
 * @Enable模块：@EnableHelloWorld -> HelloWorldImportSelector -> HelloWorldConfiguration -> helloWorld方法
 * @date 2019-08-15 10:32
 */
@Configuration // Spring模式注解
@EnableHelloWorld // Spring @Enable模块装配
@ConditionalOnSystemProperty(name = "user.name", value = "DELL") // 条件装配
public class HelloWorldAutoConfiguration {


}
