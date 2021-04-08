package com.sber.lesson9;

import java.util.function.Consumer;

@FunctionalInterface
public interface IPipeline<T> {
    void execute(Consumer<T> consumer);
}


