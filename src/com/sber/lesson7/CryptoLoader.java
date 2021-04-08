package com.sber.lesson7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class CryptoLoader extends ClassLoader {
    // название класса и путь к файлу класса
    private final HashMap<String, String> mappings;

    public CryptoLoader(HashMap<String, String> mappings) {
        this.mappings = mappings;
    }

    // читаем класс, шифруем и записываем новый файл - класс
    public void encryptClass(String path) throws Exception{
        FileInputStream fin = new FileInputStream(path);

        byte[] buffer = new byte[(int) (new File(path).length())];
        int t = fin.read(buffer);
        System.out.println("Read byte:" + t);

        fin.close();

        byte[] bufferEncrypt = new byte[buffer.length];

        for (int i = 0; i < bufferEncrypt.length; i++) {
            bufferEncrypt[i] = (byte)(buffer[i] + 1);
        }

        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/pluginRootDirectory/Secret.class");
        fos.write(bufferEncrypt, 0, buffer.length);
        fos.close();
    }

    public synchronized Class<?> loadClass(final String name) throws ClassNotFoundException {
        try {
            System.out.println("loadClass (" + name + ")");

            // если нет такого класса, то делегируем загрузку
            if (!mappings.containsKey(name)) {
                return super.findSystemClass(name);
            }

            String fileName = mappings.get(name);

            FileInputStream fin = new FileInputStream(fileName);

            byte[] buffer = new byte[(int) (new File(fileName).length())];
            int t = fin.read(buffer);
            System.out.println("Read byte:" + t);

            fin.close();

            byte[] bufferDecrypt = new byte[buffer.length];

            // дешифруем
            for (int i = 0; i < bufferDecrypt.length; i++) {
                bufferDecrypt[i] = (byte)(buffer[i] - 1);
            }

            return defineClass(name, bufferDecrypt, 0, buffer.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }
}
