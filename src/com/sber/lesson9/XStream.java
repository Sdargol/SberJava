package com.sber.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class XStream<T> {
    private final List<? extends T> data;

    private XStream(List<T> data){
        this.data = data;
    }

    public static <X> XStream<X> of(List<X> data){
        return new XStream<>(data);
    }

    public XStream<T> filter(Predicate<T> predicate){
        List<T> tmp = new ArrayList<>();
        for(T e: data){
            if(predicate.test(e)){
                tmp.add(e);
            }
        }
        return new XStream<>(tmp);
    }

    public <R> XStream<R> transform(Function<T, R> function){
        List<R> tmp = new ArrayList<>();
        for (T e: data){
            tmp.add(function.apply(e));
        }
        return new XStream<>(tmp);
    }

    public <K, V> Map<K,V> toMap(Function<T, K> key, Function<T, V> value, Supplier<Map<K, V>> mapSupplier){
        Map<K,V> map = mapSupplier.get();
        for(T e: data){
            map.put(key.apply(e), value.apply(e));
        }
        return map;
    }

    public void print(){
        System.out.println(data);
    }

}
