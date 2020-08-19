package com.zxb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跨域Controller 演示
 * @author Mr.zxb
 * @date 2019-08-26 21:14:54
 */
@Controller
public class CrossController {

    @RequestMapping("")
    public String index() {
        return "index";
    }
}
