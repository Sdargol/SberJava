package com.sber.lesson5.server;

import com.sber.lesson5.client.Message;
import com.sber.lesson5.server.account.Account;
import com.sber.lesson5.server.account.AccountStorage;

public class ParserMessage {
    private final AccountStorage db;

    public ParserMessage(AccountStorage db) {
        this.db = db;
    }

    private String stateAccount(int id) {
        Account account = db.getAccount(id);
        return "Account id: " + account.getId() +
                ", balance : " + account.balance() +
                ", blocking status: " + account.getAccountStatusBlocked() +
                ", num blocked: " + account.getNumBlocked();
    }

    private String getMoney(int id, Message msg) {
        Account account = db.getAccount(id);
        int money = Integer.parseInt(msg.getBody());
        return "Account id: " + account.getId() + ", get money: " + msg.getBody() + ", balance: " + account.getMoney(money);
    }

    private String setMoney(int id, Message msg) {
        Account account = db.getAccount(id);
        int money = Integer.parseInt(msg.getBody());
        return "Account id: " + account.getId() + ", set money: " + msg.getBody() + ", balance: " + account.setMoney(money);
    }

    public String runParser(int id, Message msg) throws MessageValidatorException {
        switch (msg.getHeader()) {
            case "stateAccount":
                return stateAccount(id);

            case "getMoney":
                moneyValidator(id,msg);
                multipleValidator(msg);
                return getMoney(id, msg);

            case "setMoney":
                multipleValidator(msg);
                return setMoney(id, msg);
        }
        return "[PARSER MESSAGE]: Not correct data in header";
    }

    private void moneyValidator(int id, Message msg) throws MessageValidatorException {
        if (db.getAccount(id).balance() < Integer.parseInt(msg.getBody())) {
            throw new MessageValidatorException("На счету пользователя недостаточно средств", msg.getBody());
        }
    }

    private void multipleValidator(Message msg) throws MessageValidatorException {
        if (Integer.parseInt(msg.getBody()) % 100 != 0) {
            throw new MessageValidatorException("Сумма должна быть кратна 100", msg.getBody());
        }
    }

}
