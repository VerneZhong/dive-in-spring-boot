package com.zxb.diveinspringboot.annotation;

import java.lang.annotation.*;

/**
 * 二级 {@link org.springframework.stereotype.Repository}
 * @author Mr.zxb
 * @date 2019-08-14 15:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelRepository
public @interface SecondLevelRepository {

    String value() default "";
}
