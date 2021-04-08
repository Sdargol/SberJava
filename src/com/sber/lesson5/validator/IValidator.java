package com.sber.lesson5.validator;

import com.sber.lesson5.client.Message;

public interface IValidator {
    void validateSymbol(String symbol) throws ValidatorSymbolException;
    void validateBodyMessage(Message msg) throws ValidatorSymbolException;
}
