package com.sber.lesson5.server;

import com.sber.lesson5.client.Client;
import com.sber.lesson5.client.Message;
import com.sber.lesson5.server.account.Account;
import com.sber.lesson5.server.account.AccountIsLockedException;
import com.sber.lesson5.server.account.AccountStorage;
import com.sber.lesson5.validator.Validator;
import com.sber.lesson5.validator.ValidatorSymbolException;

import java.util.ArrayList;

public class Server implements IServer{
    private final ArrayList<Integer> clientAuthId;
    private final AccountStorage db;
    private final Validator validator;
    private final ParserMessage parserMessage;

    public Server(){
        clientAuthId = new ArrayList<>();
        db = new AccountStorage();
        validator = new Validator();
        parserMessage = new ParserMessage(db);
    }

    @Override
    public boolean connect(int id, int[] pin) throws AccountIsLockedException {
        Account account = null;
        //если есть в бд пользователь с таким id
        if(db.getAccount(id) != null){
            account = db.getAccount(id);

            //если аккаунт заблокирован
            if(account.getAccountStatusBlocked()){
                throw new AccountIsLockedException("Аккаунт заблокирован", account.blockedTimeLeft());
                //return false;
            }
            //проверка пин, если совпадает, то делаем id авторизованным, иначе добавляем попытку
            if( account.equalsPin( convertPin(pin) ) ){
                setAuthClient(id);
                account.resetAttempt();
                return true;
            }else{
                account.addAttempt();
            }
        }
        return false;
    }

    @Override
    public String send(int id, Message msg) {
        if(!checkAuth(id)){
            return "[SERVER]: Отказано в доступе";
        }

        try {
            validator.validateBodyMessage(msg);
        } catch (ValidatorSymbolException e) {
            return e.getErrorMessage();
        }

        try {
            return "[SERVER]: " + parserMessage.runParser(id,msg);
        } catch (MessageValidatorException e) {
           return "[SERVER]: " + e.getErrorMessage();
        }
    }

    private void setAuthClient(int id){
        clientAuthId.add(id);
    }

    //поверка, если вдруг отправить данные в обход терминала
    private int[] validPin(int[] pin) {
        for (int j : pin) {
            try {
                validator.validateSymbol(String.valueOf(j));
            } catch (ValidatorSymbolException e) {
                Client.render(e.getErrorMessage());
            }
        }
        return pin;
    }

    private String convertPin(int[] pin){
        int[] inputPin = validPin(pin);
        StringBuilder outPin = new StringBuilder();

        for (int j : inputPin) {
            outPin.append(j);
        }
        return outPin.toString();
    }

    private boolean checkAuth(int id){
        return clientAuthId.contains(id);
    }

}
