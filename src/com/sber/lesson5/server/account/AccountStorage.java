package com.sber.lesson5.server.account;

import java.util.HashMap;
import java.util.Random;

public class AccountStorage {
    private final HashMap<Integer, Account> db;
    private final Random random = new Random();

    public AccountStorage(){
        db = new HashMap<>();
        fillDB();
    }

    private void fillDB(){
        db.put(0, new Account(0, random.nextInt(15313), "0000"));
        db.put(1, new Account(1, random.nextInt(17313), "0001"));
        db.put(2, new Account(2, random.nextInt(25111), "0020"));
    }

    public Account getAccount(int id){
        if(db.containsKey(id)){
            return db.get(id);
        }
        return null;
    }
}
