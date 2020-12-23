package com.sber.lesson3;

import java.util.Map;

public interface ICountMap<T> {
    //Добавляет элемент в этот контейнер
    void add(T el);

    //Возвращает количество добавлений данного элемента
    int getCount(T el);

    //Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
    int remove(T el);

    //Количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер,
    //при совпадении ключей, суммировать значения
    void addAll(ICountMap<T> source);

    //Вернуть java.util.Map. ключ - добавленный элемент,
    //значение - количество его добавлений
    Map<T, Integer> toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map<T, Integer> destination);
}
