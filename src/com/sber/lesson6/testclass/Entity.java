package com.sber.lesson6.testclass;

public class Entity {
    private String name;
    private int value;
    private Box box;

    public Entity(String name, int value, Box box) {
        this.name = name;
        this.value = value;
        this.box = box;
    }

    public Entity(){
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Box getBox() {
        return box;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Entity { " +
                "name = '" + name + '\'' +
                ", value = " + value +
                ", box = " + box +
                " }";
    }
}
