package com.sber.lesson5.terminal;

import com.sber.lesson5.client.Client;
import com.sber.lesson5.client.Message;
import com.sber.lesson5.server.IServer;
import com.sber.lesson5.server.Server;
import com.sber.lesson5.server.account.AccountIsLockedException;
import com.sber.lesson5.validator.IValidator;
import com.sber.lesson5.validator.Validator;
import com.sber.lesson5.validator.ValidatorSymbolException;

import java.util.Scanner;

public class Terminal {
    private final IValidator validator;
    private final IServer server;
    private final Scanner scanner;

    private Client client;
    private final Message msg;

    private String input;
    private boolean conn = false;

    public Terminal() {
        scanner = new Scanner(System.in);
        validator = new Validator();
        server = new Server();
        msg = new Message();
    }

    public void terminalLoop() {
        Client.render("[TERMINAL]: Terminal run!");
        while (true) {
            if (!conn) {
                Client.render("[TERMINAL]: Create connect.");
                connect();
            }

            if (!conn) {
                Client.render("[TERMINAL]: No connect. Exit from loop.");
                break;
            }

            Client.render("[TERMINAL]: Enter command: ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                Client.render("[TERMINAL]: Exit from loop.");
                break;
            }
            //Client.render(server.send(client.getId(),msg));
            parseCommand(input);
        }

        scanner.close();
        Client.render("[TERMINAL]: Terminal finish!");
    }

    private int[] enterPin() {
        int[] pin = new int[4];

        for (int i = 0; i < 4; i++) {
            Client.render("Введите " + (1 + i) + " цифру PIN кода: ");
            input = scanner.nextLine();

            try {
                validator.validateSymbol(input);
                pin[i] = Integer.parseInt(input);
            } catch (ValidatorSymbolException e) {
                i = i - 1;
                Client.render(e.getErrorMessage());
            }
        }
        return pin;
    }

    private void connect() {
        while (true) {
            Client.render("[CONNECTION SERVER]: Enter client id (or exit): ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                Client.render("[CONNECTION SERVER]: Break connect ");
                break;
            }

            Client.render("[CONNECTION SERVER]: Create client ");
            client = new Client(Integer.parseInt(input));

            Client.render("[CONNECTION SERVER]: Enter pin:  ");
            int[] pin = enterPin();

            Client.render("[CONNECTION SERVER]: Send data to server...");
            try {
                conn = server.connect(client.getId(), pin);
            } catch (AccountIsLockedException e) {
                Client.render(e.getErrorMessage());
                conn = false;
            }

            Client.render("[CONNECTION SERVER]: Server status: " + conn);

            if (conn) {
                break;
            }
        }
    }

    private String stateAccount() {
        msg.setHeader("stateAccount");
        msg.setBody("0");

        return server.send(client.getId(), msg);
    }

    private String getMoney() {
        Client.render("[GET MONEY]: Снять наличные: ");
        input = scanner.nextLine();

        msg.setHeader("getMoney");
        msg.setBody(input);

        return server.send(client.getId(), msg);
    }

    private String setMoney() {
        Client.render("[SET MONEY]: Внести наличные: ");
        input = scanner.nextLine();

        msg.setHeader("setMoney");
        msg.setBody(input);

        return server.send(client.getId(), msg);
    }

    private void parseCommand(String command) {
        switch (command) {
            case "stateAccount":
                Client.render(stateAccount());
                break;
            case "getMoney":
                Client.render(getMoney());
                break;
            case "setMoney":
                Client.render(setMoney());
                break;
            default:
                Client.render("Неизвестная команда");
        }
    }
}
