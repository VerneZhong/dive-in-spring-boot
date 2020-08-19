package com.zxb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * {@link Properties} {@link RestController}
 * @author Mr.zxb
 * @date 2019-08-23 17:22
 */
//@RestController
    @Controller
public class PropertiesRestController {

    @PostMapping(value = "/add/props",
            // 过滤媒体类型，Content-Type
            consumes = "text/properties;charset=UTF-8"
            )
    public Properties addProperties(
//            @RequestBody
                    Properties properties) {
        return properties;
    }
}
