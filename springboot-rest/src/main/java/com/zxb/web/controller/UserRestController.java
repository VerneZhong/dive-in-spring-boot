package com.zxb.web.controller;

import com.zxb.web.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User {@link RestController}
 * @author Mr.zxb
 * @date 2019-08-23 11:03
 */
@RestController
public class UserRestController {

    @PostMapping(value = "/echo/user",
            // 过滤媒体类型，Content-Type
            consumes = "application/*;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    public User getUser(@RequestBody User user) {
        return user;
    }
}
