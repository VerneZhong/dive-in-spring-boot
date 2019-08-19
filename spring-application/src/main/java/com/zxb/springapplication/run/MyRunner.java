package com.zxb.springapplication.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Mr.zxb
 * @date 2019-08-19 15:05
 */
@Component
public class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyRunner args = [" + args + "]");
    }
}
