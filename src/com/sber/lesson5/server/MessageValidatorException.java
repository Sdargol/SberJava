package com.sber.lesson5.server;

public class MessageValidatorException extends Exception{
    private String msg;
    private String body;

    public MessageValidatorException(String msg, String body){
        super(msg);
        this.msg = msg;
        this.body = body;
    }

    public String getErrorMessage(){
        return msg + ", ошибка в данных -> " + body;
    }
}
