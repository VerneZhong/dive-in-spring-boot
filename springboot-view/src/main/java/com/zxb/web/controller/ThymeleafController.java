package com.zxb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.zxb
 * @date 2019-08-21 09:34
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/thy")
    public String hello() {
        // view的逻辑名称
        return "thymeleaf";
    }

    @ModelAttribute("message")
    public String message() {
        return "helloWorld";
    }
}
