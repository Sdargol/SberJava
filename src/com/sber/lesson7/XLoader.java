package com.sber.lesson7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

class XLoader extends ClassLoader {

    // название класса и путь к файлу класса
    private final HashMap<String, String> mappings;

    XLoader(HashMap<String, String> mappings) {
        this.mappings = mappings;
    }

    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
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

            System.out.println("read byte: " + t);

            fin.close();

            return defineClass(name, buffer, 0, buffer.length);

        } catch (IOException e) {

            e.printStackTrace();

            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

}
