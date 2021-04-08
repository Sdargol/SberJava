package com.sber.lesson12;

import java.util.function.Consumer;

public final class Context {
    private final Consumer<Runnable> invokeCallback;
    private int completed;
    private int failed;
    private volatile boolean interrupted;
    private boolean finished;

    public Context(final int allTasks) {
        invokeCallback = (c) -> {
            if (!interrupted) {
                if ((completed + failed) == allTasks) {
                    setFinished();
                    c.run();
                }
            }
        };
    }

    public synchronized void addCompleted(Runnable callback) {
        completed++;
        invokeCallback.accept(callback);
    }

    public synchronized void addFailed(Runnable callback) {
        failed++;
        invokeCallback.accept(callback);
    }

    private void setFinished() {
        this.finished = true;
    }

    public int getCompletedTaskCount() {
        return this.completed;
    }

    public int getFailedTaskCount() {
        return this.failed;
    }

    public void interrupt() {
        this.interrupted = true;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public boolean isFinished() {
        return this.finished;
    }

}
