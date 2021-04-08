package com.sber.lesson12;

import com.sber.lesson11.ThreadPool;

import java.util.Random;
import java.util.concurrent.Callable;

public class Starter {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("Я начинаю выполняться");
            Thread.currentThread().sleep(1500);
            return 8 + 3;
        };


        Task<Integer> task = new Task<>(callable);

        /*for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                System.out.println("Из потока " + Thread.currentThread().getName());
                System.out.println(task.get());
            }).start();
        }*/

        //===============

        final Random random = new Random();
        final ThreadPool threadPool = new ThreadPool(3);
        final IExecutionManager executionManager = new ExecutionManager(threadPool);
        final Runnable[] tasks = new Runnable[5];
        final Runnable callback = () -> System.out.println("Все задачи обработаны, произошел вызов callback");
        final Runnable taskError = () -> {throw new RuntimeException("в какой - то задаче какая - то ошибка");};

        // создаем массив с задачами
        for (int i = 0; i < tasks.length; i++) {
            final int _i = i;
            tasks[i] = () -> {
                int r = random.nextInt(1000) + 1000;
                try {
                    Thread.currentThread().sleep(r);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("Run task " + _i + " , " + Thread.currentThread().getName() + ", задержка " + r);
            };
        }

        Context context = executionManager.execute(callback, tasks[0], tasks[1], tasks[2], tasks[3], taskError, tasks[4]);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[CONTEXT]: completed: " + context.getCompletedTaskCount());
        System.out.println("[CONTEXT]: failed: " + context.getFailedTaskCount());
        System.out.println("[CONTEXT]: interrupted: " + context.isInterrupted());
        System.out.println("[CONTEXT]: finished: " + context.isFinished());
        threadPool.shutdown();
    }
}
