package com.zxb.diveinspringboot.bootstrap;

import com.zxb.diveinspringboot.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 仓储的引导类
 * @author Mr.zxb
 * @date 2019-08-14 14:11
 */
@ComponentScan(basePackages = "com.zxb.diveinspringboot.repository")
public class RepositoryBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 获取MyFirstLevelRepository Repository Bean
        MyFirstLevelRepository myFirstLevelRepository = context.getBean("myFirstLevelRepository", MyFirstLevelRepository.class);

        System.out.println("myFirstLevelRepository = " + myFirstLevelRepository);

        // 关闭上下文
        context.close();
    }
}
