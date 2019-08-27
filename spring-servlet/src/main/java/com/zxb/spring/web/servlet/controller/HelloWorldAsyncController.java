package com.zxb.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello World 异步 {@link RestController}
 *
 * @author Mr.zxb
 * @date 2019-08-26 23:49:12
 */
@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private BlockingQueue<DeferredResult<String>> queue = new LinkedBlockingQueue<>(5);

    private final Random random = new Random();

    /**
     * 定时操作
     */
    @Scheduled(fixedRate = 5000)
    public void process() throws InterruptedException {
        DeferredResult<String> result;
        do {
            result = queue.take();
            // 随机超时时间
            int timeout = random.nextInt(100);
            // 线程等待时间
            Thread.sleep(timeout);
            // 返回结果
            result.setResult("hello result return.");
            println("执行计算结果，消耗：" + timeout + " ms.");
        } while (result != null);
    }

    @GetMapping("/hello-world")
    public DeferredResult<String> hello() {
        DeferredResult<String> deferredResult = new DeferredResult<>(50L);

//        deferredResult.setResult("hello， World");

        // 入队操作
        queue.offer(deferredResult);

        deferredResult.onCompletion(() -> println("执行结束."));

        deferredResult.onTimeout(() -> println("执行超时."));
        return deferredResult;
    }

    private static void println(Object o) {
        System.out.println("HelloWorldAsyncController [" + Thread.currentThread().getName() + "]: " + o);
    }
}
