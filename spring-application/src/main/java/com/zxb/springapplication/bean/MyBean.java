package com.zxb.springapplication.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要访问传递给SpringApplication.run(…​)的应用程序参数，
 * 则可以注入 {@link ApplicationArguments} bean。
 * ApplicationArguments接口提供对原始String[]参数以及解析的option和non-option参数的访问
 *
 * @author Mr.zxb
 * @date 2019-08-19 13:42
 */
@Component
public class MyBean {

    @Autowired
    public MyBean(ApplicationArguments arguments) {
        boolean exists = arguments.containsOption("nonOptionArgs");
        List<String> fields = arguments.getNonOptionArgs();
        System.out.println("MyBean exists = " + exists + ", args =" + fields);
    }
}
