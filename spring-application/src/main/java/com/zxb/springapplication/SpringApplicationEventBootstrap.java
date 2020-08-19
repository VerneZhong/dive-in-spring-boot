package com.zxb.springapplication;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring应用事件引导类，基于SpringFramework来实现
 * @author Mr.zxb
 * @date 2019-08-19 09:02
 */
public class SpringApplicationEventBootstrap {

    public static void main(String[] args) {

        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册应用事件监听器
        context.addApplicationListener(event -> System.out.println("监听到事件：" + event));

        // 启动上下文
        context.refresh();

        // 发送普通事件
        context.publishEvent("Hello world");
        context.publishEvent("2019");

        // 自定义事件
        context.publishEvent(new ApplicationEvent("钟学斌") {

        });

        // 关闭上下文
        context.close();
    }
}
