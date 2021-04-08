package com.sber.lesson11;

import java.util.Random;

public class Starter {
    public static void main(String[] args) {
        final Random random = new Random();
        final ThreadPool threadPool = new ThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int _i = i;
            threadPool.execute(() -> {
                int r = random.nextInt(1000) + 1000;
                try {
                    Thread.currentThread().sleep(r);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("Хорошая Run task " + _i + " , " + Thread.currentThread().getName() + ", задержка " + r);
            });
        }

        threadPool.shutdown();
    }

}
