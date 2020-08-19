package com.zxb.spring.reactive.loader;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞（串行）数据加载
 * @author Mr.zxb
 * @date 2019-08-29 09:56
 */
public class DataLoader {

    public final void load() {
        final long startTime = System.currentTimeMillis();
        doLoad();
        final long costTime = System.currentTimeMillis() - startTime;
        System.out.println("load() 总耗时：" + costTime + " ms.");
    }

    protected void doLoad() {
        loadConfigurations();
        loadUsers();
        loadOrders();
    }

    protected void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }
    protected void loadUsers() {
        loadMock("loadUsers()", 2);
    }
    protected void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            final long startTime = System.currentTimeMillis();
            final long millis = TimeUnit.SECONDS.toMillis(seconds);
            TimeUnit.MILLISECONDS.sleep(millis);
            final long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程： %s] %s 耗时： %d 毫秒\n", Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new DataLoader().load();
    }
}
