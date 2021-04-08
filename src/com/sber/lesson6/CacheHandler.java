package com.sber.lesson6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CacheHandler implements InvocationHandler {
    private final ICalculator original;
    private final HashMap<String, Integer> storage;

    public CacheHandler(ICalculator calculator) {
        original = calculator;
        storage = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isAnnotationPresent(Cache.class)) {
            if (!storage.containsKey(argsToString(args))) {
                System.out.println("Значения нет, добавляем ");
                storage.put(argsToString(args), (Integer) method.invoke(original, args));
            }
            return storage.get(argsToString(args));
        }

        return method.invoke(original, args);
    }

    private String argsToString(Object[] args) {
        StringBuilder result = new StringBuilder();
        for (Object o : args) {
            result.append(o).append(",");
        }
        return result.toString();
    }
}
