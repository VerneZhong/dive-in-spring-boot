package com.zxb.springapplication.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * SpringApplication运行监听器
 * @author Mr.zxb
 * @date 2019-08-19 09:20
 */
public class MyRunListener implements SpringApplicationRunListener {

    /**
     * 必须有有这俩参数的构造方法，否则启动失败
     * @param application
     * @param args
     */
    public MyRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        System.out.println("MyRunListener.starting()...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
