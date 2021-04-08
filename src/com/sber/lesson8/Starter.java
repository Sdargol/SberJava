package com.sber.lesson8;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Starter {
    public static void main(String[] args) {
        IService service = CacheProxy.getCacheProxy(new Service());
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.execute(() -> System.out.println("***Starter invoke service.run: " + service.run("Proxy", 5.0, new Date())));
        threadPool.execute(() -> System.out.println("***Starter invoke service.run: " + service.run("Test", 3.0, new Date())));
        threadPool.execute(() -> System.out.println("***Starter invoke service.run: " + service.run("Java", 15.0, new Date())));
        threadPool.execute(() -> System.out.println("***Starter invoke service.run: " + service.run("Proxy", 5.0, new Date())));
        threadPool.execute(() -> System.out.println("***Starter invoke service.run: " + service.run("Proxy", 5.0, new Date())));

        threadPool.shutdown();

    }
}
