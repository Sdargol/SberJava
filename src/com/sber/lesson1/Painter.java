package com.sber.lesson1;

public class Painter {
    private String name = "";

    public Painter(String name){
        this.name = name;
    }

    public void paint(Figure figure){
        System.out.println("Художник " + name + " рисует:");
        figure.paint();
    }
}
