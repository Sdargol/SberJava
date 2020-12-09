package com.sber.lesson1;

public abstract class Figure {
    private String title = "";
    private int sides = 0;

    public Figure(String title, int sides){
        this.title = title;
        this.sides = sides;
    }

    public void paint(){
        System.out.println(title + ", у которого " + sides + " сторон(ы) ");
    }

}
