package com.zxb.spring.reactive.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Flux 示例
 *
 * @author Mr.zxb
 * @date 2019-08-30 15:33
 */
public class FluxDemo {

    public static void main(String[] args) throws InterruptedException {

        println("开始运行...");
        // A -> B -> C
        Flux.just("A", "B", "C")
                // 线程池切换
//                .publishOn(Schedulers.elastic())
                // 转换
                .map(p -> p + "-")
                .subscribe(
                        // 数据消费
                        FluxDemo::println,
                        // 异常处理
                        FluxDemo::println,
                        // 完成回调
                        () -> println("任务执行完成..."),
                        // 背压操作
                        subscription -> subscription.request(Integer.MAX_VALUE));

        Thread.sleep(2000);
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "] " + object);
    }
}
