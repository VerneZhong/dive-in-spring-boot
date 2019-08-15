package com.zxb.diveinspringboot.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Java 系统属性 条件判断，基于编程方式实现
 * @author Mr.zxb
 * @date 2019-08-15 09:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    /**
     * Java系统属性名称
     * @return
     */
    String name();

    /**
     * Java系统属性值
     * @return
     */
    String value();
}
