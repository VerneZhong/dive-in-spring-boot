package com.zxb.spring.reactive.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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
//                .subscribe(
//                        // 数据消费 == onNext(T)
//                        FluxDemo::println,
//                        // 异常处理 == onError(Throwable)
//                        FluxDemo::println,
//                        // 完成回调 == onComplete()
//                        () -> println("任务执行完成..."),
//                        // 背压操作 == onSubscribe(Subscription)
//                         subscription -> {
//                             // 取消上游传输数据到下游
//                             subscription.cancel();
//                             // n请求的元素数量
//                             subscription.request(Integer.MAX_VALUE);
//                         });
                // 匿名类的方式
                .subscribe(new Subscriber<String>() {

                    private int count = 1;

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        subscription.request(3);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext() " + s);
                        if (count == 4) {
                            throw new RuntimeException("数字异常");
                        }
                        count++;
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError() " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });

        Thread.sleep(2000);
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "] " + object);
    }
}
