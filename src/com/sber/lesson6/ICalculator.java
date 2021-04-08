package com.sber.lesson6;

public interface ICalculator {
    @Cache
    @Metric
    int calc(int number);
}
