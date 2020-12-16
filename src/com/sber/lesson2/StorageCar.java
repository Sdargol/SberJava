package com.sber.lesson2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorageCar {
    private List<Car> listCar;
    private HashMap<String, ArrayList<Car>> mapCar;

    public StorageCar(List<Car> listCar){
        this.listCar = new ArrayList<>();
        this.listCar.addAll(listCar);

        this.mapCar = new HashMap<>();

        for(Car c: this.listCar){
            if(!mapCar.containsKey(c.getType())){
                mapCar.put(c.getType(), new ArrayList<>());
            }
            mapCar.get(c.getType()).add(c);
        }
    }

    public void getCarByType(String typeCar){
        if(mapCar.containsKey(typeCar)){
            System.out.println(mapCar.get(typeCar));
        } else {
            System.out.println("У нас нет автомобиля с типом " + typeCar );
        }
    }
}
