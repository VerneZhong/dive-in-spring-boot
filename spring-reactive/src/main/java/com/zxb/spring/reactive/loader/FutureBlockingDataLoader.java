package com.zxb.spring.reactive.loader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * {@link Future} 阻塞数据加载
 * @author Mr.zxb
 * @date 2019-08-29 11:21
 */
public class FutureBlockingDataLoader extends DataLoader {

    @Override
    protected void doLoad() {
        // 创建线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        runCompletely(executorService.submit(super::loadConfigurations));
        runCompletely(executorService.submit(super::loadUsers));
        runCompletely(executorService.submit(super::loadOrders));
        executorService.shutdown();
    }

    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }
}
