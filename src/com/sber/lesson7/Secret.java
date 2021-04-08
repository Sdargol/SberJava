package com.sber.lesson7;

public class Secret {
    private final int password = 1234;

    public void saySecret(){
        System.out.println("Супер секретная фраза и пароль: " + password);
    }
}
