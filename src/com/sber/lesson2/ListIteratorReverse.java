package com.sber.lesson2;

import java.util.Iterator;
import java.util.List;

public class ListIteratorReverse <T> implements Iterator<T> {

    private List<T> source = null;
    private int index = 0;

    public ListIteratorReverse(List<T> source){
        this.source = source;

        index = source.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public T next() {
        return source.get(index--);
    }

}
