package com.zxb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World {@link RestController}
 * @author Mr.zxb
 * @date 2019-08-22 17:17
 */
@RestController
public class HelloWorldController {

    @GetMapping("hello")
    public String hello(@RequestParam(required = false) String message) {
        return "hello World! " + message;
    }
}
