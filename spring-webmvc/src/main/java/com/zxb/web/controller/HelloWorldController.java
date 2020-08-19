package com.zxb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.zxb
 * @date 2019-08-19 21:12:04
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
//        model.addAttribute("message", "hello World");
        return "hello";
    }

    @RequestMapping("/error")
    public String error(@RequestParam int value) {
        return "index";
    }

}
