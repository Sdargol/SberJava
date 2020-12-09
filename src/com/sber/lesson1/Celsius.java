package com.sber.lesson1;

import java.util.ArrayList;

public class Celsius {
    private float temperature;

    private ArrayList<IConverter> converters;

    public Celsius(float temperature){
        this.temperature = temperature;
        converters = new ArrayList<>();
    }

    public void addConverter(IConverter converter){
        converters.add(converter);
    }

    public void setTemperature(float temperature){
        this.temperature = temperature;
    }

    public void convert(){
        if(converters.size() == 0){
            System.out.println("Нет конвертеров");
        }

        for(IConverter c: converters){
            System.out.println(temperature + "С = " +  c.convert(temperature));
        }
    }
}
