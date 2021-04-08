package com.sber.lesson6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanUtils {
    //копируем свойства из from в to
    public static void assign(Object to, Object from){
        Class<?> classTo = to.getClass();
        Class<?> classFrom = from.getClass();

        //геттеры из from
        Set<Method> gettersFrom = Arrays.stream(classFrom.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("get"))
                .collect(Collectors.toSet());

        //сеттеры из to
        Set<Method> settersTo = Arrays.stream(classTo.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .collect(Collectors.toSet());

        for(Method mTo: settersTo){
            for(Method mFrom: gettersFrom){
                //отбрасываем из названия метода get и set и сравниваем
                if(mTo.getName().substring(3).equals(mFrom.getName().substring(3))){

                    if(mTo.getParameterTypes().length == 1){
                        //если тип первого параметра сеттера равен типу возващаемого значения геттера, то делаем вызов
                        if(mTo.getParameterTypes()[0] == mFrom.getReturnType()){

                            try {
                                mTo.invoke(to, mFrom.invoke(from));
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        }
    }

}
