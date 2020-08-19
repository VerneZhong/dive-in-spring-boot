package com.zxb.spring.reactive.flow;

import java.util.concurrent.Flow;
import java.util.stream.IntStream;

/**
 * 订阅者 {@link java.util.concurrent.Flow.Subscriber} 的实现
 *
 * @author Mr.zxb
 * @date 2019-09-02 13:40
 * @since Java9
 */
public class MagazineSubscriber implements Flow.Subscriber<Integer> {

    public static final String JACK = "Jack";
    public static final String PETE = "Pete";

    private final long sleepTime;

    private final String subscriberName;

    private Flow.Subscription subscription;

    private int nextMagazineExpected;

    private int totalRead;

    public MagazineSubscriber(long sleepTime, String subscriberName) {
        this.sleepTime = sleepTime;
        this.subscriberName = subscriberName;
        this.nextMagazineExpected = 1;
        this.totalRead = 0;
    }

    /**
     * Publisher在被指定一个新的Subscriber时调用此方法
     *  一般来说你需要在subscriber内部保存这个subscription 实例，因为后面会需要通过她向publisher发送信号来完成：
     *  请求更多数据，或者取消订阅。 一般在这里我们会直接请求第一个数据
     * @param subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(2);
    }

    /**
     * 每当新的数据产生，这个方法会被调用
     * @param item
     */
    @Override
    public void onNext(Integer item) {
        if (item != nextMagazineExpected) {
            IntStream.range(nextMagazineExpected, item).forEach(n -> log("Oh no! I missed the magazine " + n));
            nextMagazineExpected = item;
        }
        log("Great！我有一本新杂志: " + item);
        takeSomeRest();
        nextMagazineExpected++;
        totalRead++;
    }

    /**
     * 当publisher出现异常时会调用subscriber的这个方法
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        log("哎呀!我从发布者那里得到了一个错误: " + throwable.getMessage());
    }

    /**
     * 当publisher数据推送完毕时会调用此方法，于是整个订阅过程结束
     */
    @Override
    public void onComplete() {
        System.out.println("最后！我完成了订阅，我得到了总数 " +
                totalRead + " 杂志.");
    }

    private void takeSomeRest() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(String logMessage) {
        System.out.println("<=========== [" + subscriberName + "] : " + logMessage);
    }

    public String getSubscriberName() {
        return subscriberName;
    }
}
