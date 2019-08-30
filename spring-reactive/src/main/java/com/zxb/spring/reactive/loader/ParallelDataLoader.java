package com.zxb.spring.reactive.loader;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并行数据加载
 * @author Mr.zxb
 * @date 2019-08-29 10:40
 */
public class ParallelDataLoader extends DataLoader {

    /**
     * 并行计算
     */
    @Override
    protected void doLoad() {
        // 创建线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);

        // 耗时 >= 1s
        completionService.submit(super::loadConfigurations, null);
        // 耗时 >= 2s
        completionService.submit(super::loadUsers, null);
        // 耗时 >= 3s
        completionService.submit(super::loadOrders, null);

        int count = 0;
        while (count < 3) {
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new ParallelDataLoader().load();

        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
