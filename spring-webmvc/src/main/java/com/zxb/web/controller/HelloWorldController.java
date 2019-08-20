package com.zxb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.zxb
 * @date 2019-08-19 21:12:04
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index() {
        return "index";
    }
}
