package com.sber.lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Service implements IService{

    @Override
    public List<String> run(String item, double value, Date date) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(item, Double.toString(value), date.toString()));
    }

}
