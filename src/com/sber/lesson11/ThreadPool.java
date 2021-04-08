package com.sber.lesson11;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {
    private final ConcurrentLinkedQueue<Runnable> queue;
    private final ArrayList<Thread> threads;
    private volatile boolean isRunning;

    public ThreadPool(int countThread) {
        queue = new ConcurrentLinkedQueue<>();
        threads = new ArrayList<>();
        isRunning = true;

        initThreadPool(countThread);
    }

    private void initThreadPool(int countThread) {
        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    getTask().run();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        };

        for (int i = 0; i < countThread; i++) {
            threads.add(new Thread(r));
        }

        for (Thread t : threads) {
            t.start();
        }
    }

    private synchronized Runnable getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new InterruptedException("Остановка потока " + Thread.currentThread().getName());
            }
        }
        return queue.poll();
    }

    public synchronized void execute(Runnable task) {
        if (isRunning) {
            queue.add(task);
            notifyAll();
        }
    }

    public void shutdown() {
        execute(() -> {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        });

        isRunning = false;
    }

}
