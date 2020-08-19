package com.zxb.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  切面通知 {@link HelloWorldController}
 * @author Mr.zxb
 * @date 2019-08-20 10:50
 */
@ControllerAdvice(assignableTypes = HelloWorldController.class)
public class HelloWorldControllerAdvice {

    /**
     * 参数注入的方式，直接读取ModelAttribute设置的参数
     * 设置请求头
     * @param acceptLanguage
     * @return
     */
    @ModelAttribute("acceptLanguage")
    public String acceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage) {
        return acceptLanguage;
    }

    /**
     * 设置cookie
     * @param sessionId
     * @return
     */
    @ModelAttribute("sessionId")
    public String sessionId(@CookieValue("JSESSIONID") String sessionId) {
        return sessionId;
    }

    @ModelAttribute("message")
    public String message() {
        return "Hello World, message.";
    }

    /**
     * 异常处理
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> onException(Throwable throwable) {
        return ResponseEntity.ok(throwable.getMessage());
    }
}
