package com.zxb.spring.web.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Hello World 异步 {@link RestController}
 * @author Mr.zxb
 * @date 2019-08-26 23:49:12
 */
@RestController
public class HelloWorldAsyncController {

    @GetMapping("/hello-world")
    public DeferredResult<String> hello() {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        deferredResult.setResult("hello， World");

        deferredResult.onCompletion(() -> System.out.println("执行结束"));

        return deferredResult;
    }
}
