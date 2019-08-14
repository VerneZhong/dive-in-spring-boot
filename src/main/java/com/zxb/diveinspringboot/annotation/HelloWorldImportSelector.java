package com.zxb.diveinspringboot.annotation;

import com.zxb.diveinspringboot.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Hello World {@link org.springframework.context.annotation.ImportSelector} 的实现
 *
 * @author Mr.zxb
 * @date 2019-08-14 17:29
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
