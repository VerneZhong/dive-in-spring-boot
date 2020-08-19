package com.zxb.springapplication.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * After {@link HelloWorldApplicationContextInitializer} 之后执行，低优先级
 *
 * @author Mr.zxb
 * @date 2019-08-15 16:26
 */
public class AfterHelloWorldApplicationContextInitializer implements ApplicationContextInitializer , Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("After application.id = " + applicationContext.getId());
    }

    /**
     * 最低优先级
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
