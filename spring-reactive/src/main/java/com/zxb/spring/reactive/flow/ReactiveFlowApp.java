package com.zxb.spring.reactive.flow;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Mr.zxb
 * @date 2019-09-02 14:08
 */
public class ReactiveFlowApp {

    public static final int NUMBER_OF_MAGAZINES = 20;

    public static final long MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE = 2;

    private void magazineDeliveryExample(long sleepTimeJack, long sleepTimePete, int maxStorageInPO) throws InterruptedException {
        // 实例化发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(ForkJoinPool.commonPool(), maxStorageInPO);

        // 实例化2个订阅者
        MagazineSubscriber jack = new MagazineSubscriber(sleepTimeJack, MagazineSubscriber.JACK);
        MagazineSubscriber pete = new MagazineSubscriber(sleepTimePete, MagazineSubscriber.PETE);

        publisher.subscribe(jack);
        publisher.subscribe(pete);

        log("每位订阅者打印20本杂志，并在出版商中留出空间 "
                + maxStorageInPO + ". 他们有 " + MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE +
                " 消耗每本杂志的秒数.");

        IntStream.rangeClosed(1, 20).forEach(number -> {
            System.out.println("提供杂志 " + number + " 对消费者而言");
            int lag = publisher.offer(number,
                    MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE,
                    TimeUnit.SECONDS,
                    ((subscriber, integer) -> {
                        subscriber.onError(new RuntimeException("嘿 " + ((MagazineSubscriber) subscriber)
                                .getSubscriberName() + "! 得到杂志你太慢了" +
                                " 我们没有更多的空间! " +
                                "我会删除你的杂志: " + integer));
                        return false;
                    }));
            if (lag < 0) {
                log("删除 " + (-lag) + " 杂志");
            } else {
                log("最慢的消费者 " + lag +
                        " 杂志总共被拿起");
            }
        });

        // Blocks until all subscribers are done (this part could be improved
        // with latches, but this way we keep it simple)
        while (publisher.estimateMaximumLag() > 0) {
            Thread.sleep(500L);
        }

        // Closes the publisher, calling the onComplete() method on every subscriber
        publisher.close();

        // give some time to the slowest consumer to wake up and notice
        // that it's completed
        Thread.sleep(Math.max(sleepTimeJack, sleepTimePete));
    }

    private static void log(final String message) {
        System.out.println("===========> " + message);
    }

    public static void main(String[] args) throws InterruptedException {
        final ReactiveFlowApp reactiveFlowApp = new ReactiveFlowApp();

        System.out.println("### CASE 1：订户很快，缓冲区大小在这种情况下并不那么重要.");
        reactiveFlowApp.magazineDeliveryExample(100L, 100L, 2);

//        System.out.println("\n### CASE 2: 缓慢的订阅者，但发布者的缓冲区大小足以保留所有项目，直到他们被拿起.");
//        reactiveFlowApp.magazineDeliveryExample(1000L, 3000L, NUMBER_OF_MAGAZINES);

//        System.out.println("\n### CASE 3: 缓慢的订阅者，以及发布者方面的缓冲区大小非常有限，因此保持慢速订阅者的控制非常重要.");
//        reactiveFlowApp.magazineDeliveryExample(1000L, 3000L, 8);
    }
}
