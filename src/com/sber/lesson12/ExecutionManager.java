package com.sber.lesson12;

import com.sber.lesson11.ThreadPool;

public class ExecutionManager implements IExecutionManager{
    private final ThreadPool threadPool;

    public ExecutionManager(ThreadPool threadPool){
        this.threadPool = threadPool;
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Context context = new Context(tasks.length);
        for (Runnable task : tasks) {
            threadPool.execute(() ->{
                try {
                    if(!context.isInterrupted()){
                        task.run();
                        context.addCompleted(callback);
                    } else {
                        throw new InterruptedException("выполнение остановлено из context");
                    }
                } catch (Exception e) {
                    System.out.println("Исключение в задаче из ExecutionManager, " + e.getMessage());
                    context.addFailed(callback);
                }
            });
        }
        return context;
    }
}
