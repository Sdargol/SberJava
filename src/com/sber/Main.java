package com.sber;

import com.sber.lesson1.*;
import com.sber.lesson2.Car;
import com.sber.lesson2.StorageCar;
import com.sber.lesson2.TextWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //lesson1();

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
}
