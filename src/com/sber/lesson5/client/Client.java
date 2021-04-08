package com.sber.lesson5.client;

public class Client {
    private final int id;

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static void render(String content) {
        System.out.println(content);
    }
}
