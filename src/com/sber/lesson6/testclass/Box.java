package com.sber.lesson6.testclass;

public class Box {
    private final int size;
    private final String item;

    public Box(int size, String item){
        this.size = size;
        this.item =  item;
    }

    public int getSize() {
        return size;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Box {" +
                " size = " + size +
                ", item = '" + item + '\'' +
                " }";
    }
}
