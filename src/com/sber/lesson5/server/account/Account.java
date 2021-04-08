package com.sber.lesson5.server.account;

public class Account {
    private final int id;
    private int money;
    private int attempts;
    private int numBlocked;
    private final String pin;
    private long blockingTime;

    public Account(int id, int money, String pin){
        this.id = id;
        this.money = money;
        this.pin = pin;
    }

    public int getMoney(int money){
        this.money = this.money - money;
        return this.money;
    }

    public int setMoney(int money){
        this.money = this.money + money;
        return this.money;
    }

    public int balance(){
        return money;
    }

    public int getId(){
        return id;
    }

    public boolean equalsPin(String pin){
        return this.pin.equals(pin);
    }

    public boolean getAccountStatusBlocked(){
        return System.currentTimeMillis() < blockingTime;
    }

    private void blockAccount(){
        ++numBlocked;
        blockingTime = System.currentTimeMillis() + 10_000;
    }

    public long blockedTimeLeft(){
        if (getAccountStatusBlocked()){
            return (blockingTime - System.currentTimeMillis()) / 1000;
        }
        return 0;
    }

    public void addAttempt(){
        attempts = attempts + 1;

        if(attempts == 3){
            blockAccount();
        }
    }

    public void resetAttempt(){
        attempts = 0;
    }

    public int getNumBlocked(){
        return numBlocked;
    }
}
