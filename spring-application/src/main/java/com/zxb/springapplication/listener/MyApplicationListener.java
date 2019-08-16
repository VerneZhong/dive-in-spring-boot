package com.zxb.springapplication.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * {@link ApplicationListener} 监听 {@link ContextRefreshedEvent}
 * @author Mr.zxb
 * @date 2019-08-16 10:34
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 当事件到达的时候，处理
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("My: " + event.getApplicationContext().getId() + ", timestamp: " + event.getTimestamp());
    }
}
