package com.sber.lesson5.server;

import com.sber.lesson5.client.Message;
import com.sber.lesson5.server.account.AccountIsLockedException;

public interface IServer {
    boolean connect(int id, int[] pin) throws AccountIsLockedException;
    String send(int id, Message msg);
}
