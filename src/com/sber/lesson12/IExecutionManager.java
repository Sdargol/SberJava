package com.sber.lesson12;

public interface IExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
