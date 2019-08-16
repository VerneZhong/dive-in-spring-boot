package com.zxb.springapplication.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * After my applicationListener {@link ApplicationListener} 监听 {@link ContextRefreshedEvent}
 * @author Mr.zxb
 * @date 2019-08-16 10:38
 */
//@Order
public class AfterMyApplicationListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("After My: " + event.getApplicationContext().getId() + ", timestamp: " + event.getTimestamp());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
