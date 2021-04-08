package com.sber.lesson6;

import com.sber.lesson6.testclass.Box;
import com.sber.lesson6.testclass.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Starter {
    public static void main(String[] args) {
        ICalculator calculator = new Calculator();
        CacheHandler cacheHandler = new CacheHandler(calculator);

        ICalculator calculatorCache = (ICalculator) Proxy.newProxyInstance(ICalculator.class.getClassLoader(),
                calculator.getClass().getInterfaces(),
                cacheHandler);

        Class<Calculator> classCalculator = Calculator.class;
        Method[] methodCalculator = classCalculator.getMethods();
        Field[]  fieldCalculator = classCalculator.getDeclaredFields();

        /*System.out.println(calculator.calc(5));

        System.out.println(calculatorCache.calc(7));
        System.out.println(calculatorCache.calc(7));
        System.out.println(calculatorCache.calc(5));
        System.out.println(calculatorCache.calc(5));
        System.out.println(calculatorCache.calc(5));*/

        //metricTest(calculator);

        //printMethod(methodCalculator);
        //testFields(fieldCalculator,calculator);

        Box b1 = new Box(5, "Apple");
        Entity e1 = new Entity("Store", 100, b1);
        Entity e2 = new Entity();

        System.out.println("Исходный вариант: " + e1);
        System.out.println("Исходный вариант: " + e2);

        BeanUtils.assign(e2,e1);

        System.out.println("После assign: " + e1);
        System.out.println("После assign: " + e2);

    }

    public static void printMethod(Method[] methodCalculator){
        System.out.println("Методы класса Calculator: ");
        for (Method m: methodCalculator){
            System.out.println(m.getName());
        }
    }

    public static void testFields(Field[]  fieldCalculator, ICalculator calculator){
        System.out.println("Fields Calculator: ");
        for (Field f: fieldCalculator){
            int modifiers = f.getModifiers();

            if(Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)){
                if(f.getType() == String.class){
                    try {
                        if( (f.get(calculator)).equals(f.getName()) ){
                            System.out.println("Название поля " + f.getName() + " совпадает со значением");
                        }else{
                            System.out.println("Название поля " + f.getName() + " не совпадает со значением " + (f.get(calculator)));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(f);
        }
    }

    public static void metricTest(ICalculator calculator){
        PerformanceProxy performanceProxy = new PerformanceProxy(calculator);

        ICalculator calculatorMetric = (ICalculator) Proxy.newProxyInstance(ICalculator.class.getClassLoader(),
                calculator.getClass().getInterfaces(),
                performanceProxy);

        System.out.println(calculatorMetric.calc(5));

    }


}
