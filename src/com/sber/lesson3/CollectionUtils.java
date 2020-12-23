package com.sber.lesson3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<T> source, T el) {
        return source.indexOf(el);
    }

    public static <T> List<T> limit(List<T> source, int size) {
        List<T> al= new ArrayList<>(source);
        al = al.stream().limit(size).collect(Collectors.toList());
        return al;
    }

    public static <T> void add(List<? super T> source, T el) {
        source.add(el);
    }

    public static <T> void removeAll(List<T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<T> c1, List<T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<T> c1, List<T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        List<T> l = list.stream()
                .filter(el -> (min.compareTo(el) <= 0) && (max.compareTo(el) >= 0))
                .sorted(Comparable::compareTo)
                .collect(Collectors.toList());
        return l;
    }

    public static <T> List<T> range(List<T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> l = list.stream()
                .filter(el -> (comparator.compare(min,el) <= 0) && (comparator.compare(max,el) >= 0))
                .sorted(comparator)
                .collect(Collectors.toList());
        return l;
    }
}
