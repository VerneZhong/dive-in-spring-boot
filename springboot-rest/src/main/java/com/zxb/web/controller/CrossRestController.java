package com.zxb.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.zxb
 * @date 2019-08-26 21:25:37
 */
@RestController
public class CrossRestController {

//    @CrossOrigin("*")  // 跨域注解，跨域的第一种方式 *允许所有路径访问
    @GetMapping("cross")
    public String hello() {
        return "cross";
    }
}
