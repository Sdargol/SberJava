package com.sber;

import com.sber.lesson1.*;
import com.sber.lesson2.Car;
import com.sber.lesson2.StorageCar;
import com.sber.lesson2.TextWork;
import com.sber.lesson3.CollectionUtils;
import com.sber.lesson3.CountMap;
import com.sber.lesson3.ICountMap;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //lesson1();
        //lesson2();

        ICountMap<Integer> cm = new CountMap<>();
        ICountMap<Integer> cm1 = new CountMap<>();
        Map<Integer, Integer> m;

        cm.add(10); //заполняем cm
        cm.add(10);
        cm.add(10);
        cm.add(1);
        cm.add(3);

        cm1.add(5); //заполняем cm1
        cm1.add(5);
        cm1.add(2);

        System.out.println("сколько раз добавили 10 : " + cm.getCount(10));
        System.out.println("удалили 1 и показали сколько раз добавили : " + cm.remove(1));
        System.out.println("разных элементов в cm : " + cm.size());
        cm.addAll(cm1);
        System.out.println("разных элементов в cm (после добавления из cm1) : " + cm.size());

        m = cm.toMap(); // new HashMap<>(cm)
        System.out.println(m); //{2=1, 10=3, 3=1, 5=2}
        cm.add(7);
        cm.add(7);
        System.out.println(m); //{2=1, 10=3, 3=1, 5=2}
        cm.toMap(m); // destination.putAll(cm)
        System.out.println(m); //{2=1, 10=3, 3=1, 5=2, 7=2}
        m = cm1.toMap();
        System.out.println(m); //{5=2, 2=1}
        cm.toMap(m);
        System.out.println(m);//{2=1, 10=3, 3=1, 5=2, 7=2}

        List<Integer> al = CollectionUtils.newArrayList();
        List<Integer> al1 = CollectionUtils.newArrayList();
        Comparator<Integer> comp = (a,b) -> a - b;
        al.add(10);
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(5);

        al1.add(10);
        al1.add(99);

        System.out.println(al);
        System.out.println(CollectionUtils.indexOf(al,10));
        System.out.println(CollectionUtils.limit(al,3));

        System.out.println(CollectionUtils.containsAny(al,al1));
        //System.out.println(CollectionUtils.range(al,2,5));
        System.out.println(CollectionUtils.range(al,5,10, comp));
        CollectionUtils.removeAll(al,al1);
        System.out.println(al);

    }

    public static void lesson1() {
        int[] arr = {111, 7, 3, 5, 2, 9, 2, 4, 1, 110};

        Figure[] figures = {new Circle(), new Rect(), new Triangle(), new Square()};
        Painter painter = new Painter("Дмитрий");

        IConverter celsiusToFahrenheit = (t) -> (t * 9 / 5) + 32 + "F";
        IConverter celsiusToKelvin = (t) -> t + 273.15f + "K";

        Celsius celsius = new Celsius(36);

        ArrayMethod.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Индекс элемента равен: " + ArrayMethod.binSearch(arr, 111));

        for (Figure f : figures) {
            painter.paint(f);
        }

        celsius.addConverter(celsiusToFahrenheit);
        celsius.addConverter(celsiusToKelvin);

        celsius.convert();
        celsius.setTemperature(25);
        celsius.convert();

    }

    public static void lesson2() {
        TextWork tw = new TextWork();

        tw.numDifferentWords();
        tw.allUniqueWord();
        tw.printSortWord();

        tw.printLine(0);
        tw.printReverse();

        List<Car> lc = new ArrayList<>(Arrays.asList(
                new Car("Lada", "sedan"),
                new Car("Lada", "hatchback"),
                new Car("Mercedes", "crossover"),
                new Car("BMW", "sedan"),
                new Car("Ford", "sedan")
        ));

        StorageCar sc = new StorageCar(lc);

        sc.getCarByType("sedan");
    }
}
