package com.sber.lesson12;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile boolean error;

    public Task(Callable<? extends T> callable){
        this.callable = callable;
        this.result = null;
        this.error = false;
    }

    public T get() throws RuntimeException {
        if( (result == null) && !error ) {
            synchronized (this){
                if( (result == null) && !error ){
                    try {
                        result = callable.call();
                        return result;
                    } catch (Exception e) {
                        error = true;
                        throw new RuntimeException("Ошибка в callableEx");
                    }
                }
            }
        }
        if(error){
            throw new RuntimeException("Ошибка в callableEx");
        }
        return result;
    }

}
