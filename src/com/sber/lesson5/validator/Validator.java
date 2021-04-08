package com.sber.lesson5.validator;

import com.sber.lesson5.client.Message;

public class Validator implements IValidator {

    @Override
    public void validateSymbol(String symbol) throws ValidatorSymbolException {
        try{
            Integer.parseInt(symbol);
        }catch (NumberFormatException e){
            throw new ValidatorSymbolException("[ERROR]: PIN код должен состоять только из цифр от 0 до 9");
        }

        if(Integer.parseInt(symbol) < 0 || Integer.parseInt(symbol) > 9){
            throw new ValidatorSymbolException("[ERROR]: PIN код должен состоять только из цифр от 0 до 9");
        }
    }

    @Override
    public void validateBodyMessage(Message msg) throws ValidatorSymbolException {
        try{
            Integer.parseInt(msg.getBody());
        }catch (NumberFormatException e){
            throw new ValidatorSymbolException("[ERROR]: Not correct data in body");
        }
    }
}
