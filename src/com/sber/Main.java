package com.sber;

import com.sber.lesson1.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    int[] arr = {111,7,3,5,2,9,2,4, 1,110};

        Figure[] figures = {new Circle(), new Rect(),new Triangle(), new Square()};
        Painter painter = new Painter("Дмитрий");

        IConverter celsiusToFahrenheit = (t) -> (t * 9/5) + 32 + "F";
        IConverter celsiusToKelvin = (t) -> t + 273.15f + "K" ;

        Celsius celsius = new Celsius(36);

        ArrayMethod.sort(arr);
	    System.out.println(Arrays.toString(arr));
        System.out.println("Индекс элемента равен: " + ArrayMethod.binSearch(arr,111));

        for (Figure f: figures){
            painter.paint(f);
        }

        celsius.addConverter(celsiusToFahrenheit);
        celsius.addConverter(celsiusToKelvin);

        celsius.convert();
        celsius.setTemperature(25);
        celsius.convert();

    }
}
