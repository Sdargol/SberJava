package com.sber.lesson6;

public class Calculator implements ICalculator{
    public static final String FACTORIAL = "FACTORIAL";
    public static final String TEST = "HELLO";

    @Override
    public int calc(int number) {
        int result = 1;

        for (int i = 1; i <= number ; i++) {
            result = result * i;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
