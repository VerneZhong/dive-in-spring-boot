package com.zxb.springapplication.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 如果您需要在SpringApplication启动后运行某些特定代码，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，该方法在SpringApplication.run(…​)完成之前调用。
 * @author Mr.zxb
 * @date 2019-08-19 15:05
 */
@Component
public class MyRunner implements CommandLineRunner, ApplicationRunner {

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println("MyRunner CommandLineRunner run arg = [" + args[i] + "]");
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyRunner ApplicationRunner run args = " + args.getNonOptionArgs());
    }
}
