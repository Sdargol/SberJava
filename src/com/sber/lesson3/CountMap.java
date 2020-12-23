package com.sber.lesson3;

import java.util.HashMap;
import java.util.Map;

public class CountMap<T> implements ICountMap<T> {

    private final HashMap<T, Integer> cm;

    public CountMap() {
        cm = new HashMap<>();
    }

    @Override
    public void add(T el) {
        cm.merge(el, 1, Integer::sum);
    }

    @Override
    public int getCount(T el) {
        if (cm.containsKey(el)) {
            return cm.get(el);
        }
        return -1;
    }

    @Override
    public int remove(T el) {
        if (cm.containsKey(el)) {
            int count = cm.get(el);
            cm.remove(el);
            return count;
        }
        return -1;
    }

    @Override
    public int size() {
        return cm.size();
    }

    @Override
    public void addAll(ICountMap<T> source) {
        var s = source.toMap().entrySet();

        for (Map.Entry<T, Integer> e : s) {
            cm.merge(e.getKey(), e.getValue(), Integer::sum);
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(cm);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(cm);
    }

}
