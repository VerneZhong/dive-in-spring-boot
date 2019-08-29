package com.zxb.spring.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * 链式数据加载
 *
 * @author Mr.zxb
 * @date 2019-08-29 14:36
 */
public class CompletableFutureDataLoader extends DataLoader {

    @Override
    protected void doLoad() {
        CompletableFuture.runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                // 完成时回调
                .whenComplete((r, tx) -> System.out.println("[" + Thread.currentThread().getName() + "]加载完成."))
                // 等待完成
                .join();
    }

    public static void main(String[] args) {
        new CompletableFutureDataLoader().load();
    }
}
