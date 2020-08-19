package com.zxb.web.external.configuration.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 外部化配置属性源 {@link PropertySource} 顺序测试用例
 * PropertySource顺序：
 *  @TestPropertySource#properties
 *  @SpringBootTest#properties
 *  @TestPropertySource#locations
 *
 * 如何理解 PropertySource ?
 *  带有名称的属性源，Properties文件、Map、YAML文件
 *
 * 什么是Environment 抽象?
 *  Environment与PropertySources是1对1，PropertySources与PropertySource是1对N
 *  ConfigurableEnvironment 与 MutablePropertySources 是1对1
 *
 * @author Mr.zxb
 * @date 2019-09-10 10:28
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "user.id=9",
        locations = "classpath:/META-INF/default.properties")
@SpringBootTest(
//        properties = "user.id=8",
        classes = {PropertySourceOrderTest.class},
webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Configuration
@org.springframework.context.annotation.PropertySource(name = "testPropertySources", value = "classpath:/META-INF/default.properties")
public class PropertySourceOrderTest {

    @Value("${user.id}")
    private Long userId;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void testEnvironment() {
        Assert.assertSame(applicationContext.getEnvironment(), environment);
    }

    @Test
    public void testUserId() {
        Assert.assertEquals(new Long(1), userId);
    }

    @Test
    public void testPropertySources() {
        environment.getPropertySources().forEach(propertySource -> System.out.printf("PropertySource[名称：%s]：%s\n", propertySource.getName(), propertySource));
    }
}
