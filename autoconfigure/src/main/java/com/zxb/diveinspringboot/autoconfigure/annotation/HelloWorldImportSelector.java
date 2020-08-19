package com.zxb.diveinspringboot.autoconfigure.annotation;

import com.zxb.diveinspringboot.autoconfigure.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Hello World {@link ImportSelector} 的实现
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
