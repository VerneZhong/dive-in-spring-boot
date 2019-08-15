package com.zxb.diveinspringboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * 基于编程方式条件装配
 * @author Mr.zxb
 * @date 2019-08-15 09:25
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        final Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        final String name = String.valueOf(attributes.get("name"));

        final String value = String.valueOf(attributes.get("value"));

        final String systemProperty = System.getProperty(name);

        return Objects.equals(value, systemProperty);
    }
}
