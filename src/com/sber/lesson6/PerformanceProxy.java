package com.sber.lesson6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {
    private final ICalculator original;

    public PerformanceProxy(ICalculator calculator){
        original = calculator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Metric.class)){
            long startTime = System.currentTimeMillis();
            Object result = method.invoke(original,args);
            System.out.println("Время выполнения метода " + method.getName() + ": " + (System.currentTimeMillis() - startTime) + " ms");
            return result;
        }
        return method.invoke(original,args);
    }
}
