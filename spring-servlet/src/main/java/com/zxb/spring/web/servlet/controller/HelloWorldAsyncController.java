package com.zxb.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.*;

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

    /**
     * DeferredResult 和 Schedule 实现异步操作
     *
     * @return
     */
    @GetMapping("/hello-world")
    public DeferredResult<String> hello() {
        DeferredResult<String> deferredResult = new DeferredResult<>(50L);

        // 同步操作
//        deferredResult.setResult("hello， World");

        // 入队操作
        queue.offer(deferredResult);

        deferredResult.onCompletion(() -> println("执行结束."));

        deferredResult.onTimeout(() -> println("执行超时."));
        return deferredResult;
    }

    /**
     * Callable异步操作
     *
     * @return
     */
    @GetMapping("/call")
    public Callable<String> call() {
        final long startTime = System.currentTimeMillis();
        println("执行call.");
        return () -> {
            println("执行 call 计算结果，消耗：" + (System.currentTimeMillis() - startTime) + " ms.");
            return "hello call";
        };
    }

    @GetMapping("/completion-stage")
    public CompletionStage<String> completionStage() {
        final long startTime = System.currentTimeMillis();
        println("执行 completionStage.");
        return CompletableFuture.supplyAsync(() -> {
            println("执行 completionStage 计算结果，消耗：" + (System.currentTimeMillis() - startTime) + " ms.");
            return "hello completionStage";
        });
    }

    private static void println(Object o) {
        System.out.println("HelloWorldAsyncController [" + Thread.currentThread().getName() + "]: " + o);
    }

}
