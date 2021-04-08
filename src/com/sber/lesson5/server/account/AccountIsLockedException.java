package com.sber.lesson5.server.account;

public class AccountIsLockedException extends Exception{
    private final long blockTime;
    private final String msg;

    public AccountIsLockedException(String msg, long blockTime){
        super(msg);
        this.msg = msg;
        this.blockTime = blockTime;
    }

    public String getErrorMessage(){
        return "[ERROR]: " + msg + ", осталось до разблокировки: " + blockTime + " c.";
    }
}
