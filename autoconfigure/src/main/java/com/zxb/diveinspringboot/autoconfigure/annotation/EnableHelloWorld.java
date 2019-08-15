package com.zxb.diveinspringboot.autoconfigure.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Mr.zxb
 * @date 2019-08-14 17:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
