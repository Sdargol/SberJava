package com.sber.lesson9;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CStream<T> {
    private final IPipeline<T> pipeline;

    private CStream(IPipeline<T> pipeline){
        this.pipeline = pipeline;
    }

    public static <T> CStream<T> of(List<T> list){
        return new CStream<>(list::forEach);
    }

    public CStream<T> filter(Predicate<T> predicate){
        return new CStream<>(consumer -> {
            pipeline.execute(item -> {
                if(predicate.test(item)){
                    consumer.accept(item);
                }
            });
        });
    }

    public <R> CStream<R> transform(Function<T, R> function){
        return new CStream<>(consumer -> {
            pipeline.execute(item -> consumer.accept(function.apply(item)));
        });

    }

    public <K, V> Map<K, V> toMap(Function<T, K> key, Function<T, V> value, Supplier<Map<K, V>> mapSupplier){
        Map<K,V> map = mapSupplier.get();
        Consumer<T> consumer = item -> map.put(key.apply(item), value.apply(item));
        pipeline.execute(consumer);
        return map;
    }

    public void forEach(Consumer<T> consumer){
        pipeline.execute(consumer);
    }

}
