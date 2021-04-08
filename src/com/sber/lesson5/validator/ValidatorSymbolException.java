package com.sber.lesson5.validator;

public class ValidatorSymbolException extends Exception {
    private final String errorMessage;

    public ValidatorSymbolException(String msg){
        super(msg);
        errorMessage = msg;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
