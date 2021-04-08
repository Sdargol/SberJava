package com.sber.lesson8;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CacheProxy implements InvocationHandler {
    private final IService service;
    private final ConcurrentHashMap<String, Object> memoryCache;

    private CacheProxy(IService service) {
        this.service = service;
        this.memoryCache = new ConcurrentHashMap<>();
    }

    public static IService getCacheProxy(IService service) {
        CacheProxy handler = new CacheProxy(service);
        return (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
                service.getClass().getInterfaces(),
                handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ConcurrentHashMap<String, Object> cache = initCache(method);
        System.out.println(cache);

        if (method.isAnnotationPresent(Cache.class)) {
            System.out.println("Метод можно кэшировать");

            String key = generateKey(method, args);

            if (cache.containsKey(key)) {
                System.out.println("В кэше есть данные, возвращаю ");
                return cache.get(key);
            } else {
                System.out.println("В кэше нет данных, вызываю метод и сериализую кэш");
                //cache.put(key, method.invoke(service, args));
                cache.put(key, runMethod(method, args));

                if (method.getDeclaredAnnotation(Cache.class).cacheType() == CacheType.FILE) {
                    serializeCache(method, cache);
                }
            }

            return cache.get(key);
        }

        return method.invoke(service, args);
    }

    private String generateKey(Method method, Object[] args) {
        LinkedHashSet<Class<?>> argsType = new LinkedHashSet<>(Arrays.asList(method.getDeclaredAnnotation(Cache.class).identityBy()));
        System.out.println("size argsType: " + argsType.size());
        ArrayList<String> identityArgsValues = new ArrayList<>();

        identityArgsValues.add(method.getName());

        if (argsType.size() == 0) {
            for (Object arg : args) {
                identityArgsValues.add(arg.toString());
            }
            return String.join(",", identityArgsValues);
        }

        for (Class<?> c : argsType) {
            for (Object o : args) {
                if (o.getClass() == c) {
                    identityArgsValues.add(o.toString());
                }
            }
        }

        System.out.println(String.join(",", identityArgsValues));
        return String.join(",", identityArgsValues);
    }

    private synchronized void serializeCache(Method method, ConcurrentHashMap<String, Object> cache) {
        ConcurrentHashMap<String, Object> tmpCache;
        Cache cacheAnnotation = method.getDeclaredAnnotation(Cache.class);
        String fileName;
        String path;

        if (cacheAnnotation.cacheFileName().equals("method")) {
            //path = System.getProperty("user.dir") + "/cache/" + method.getName() + ".bin";
            fileName = method.getName();
            tmpCache = deserializeCache(method.getName());
        } else {
            //path = System.getProperty("user.dir") + "/cache/" + cacheAnnotation.cacheFileName() + ".bin";
            fileName = cacheAnnotation.cacheFileName();
            tmpCache = deserializeCache(cacheAnnotation.cacheFileName());
        }

        path = System.getProperty("user.dir") + "/cache/" + fileName + ".bin";
        cache.putAll(tmpCache);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(cache);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (cacheAnnotation.toZip()) {
            writeZip(fileName);
        }
    }

    private ConcurrentHashMap<String, Object> deserializeCache(String fileName) {
        String path = System.getProperty("user.dir") + "/cache/" + fileName + ".bin";

        ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

        if (!new File(path).exists()) {
            System.out.println("Такого файла не существует");
            return cache;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            cache = (ConcurrentHashMap<String, Object>) ois.readObject();
            //System.out.println(cache);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return cache;
    }

    private synchronized ConcurrentHashMap<String, Object> initCache(Method method) {
        ConcurrentHashMap<String, Object> cache;
        Cache declaredAnnotation = method.getDeclaredAnnotation(Cache.class);

        if (declaredAnnotation.cacheType() == CacheType.FILE) {
            System.out.println("Загружаем данные из файла");
            if (declaredAnnotation.cacheFileName().equals("method")) {
                cache = deserializeCache(method.getName());
            } else {
                cache = deserializeCache(declaredAnnotation.cacheFileName());
            }
            return cache;
        }

        return memoryCache;
    }

    private Object runMethod(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Cache cacheAnnotation = method.getDeclaredAnnotation(Cache.class);

        if (cacheAnnotation.listSize() == -1) {
            System.out.println("list size default (-1), invoke method and return result");
            return method.invoke(service, args);
        }

        int listSize = cacheAnnotation.listSize();
        List<?> result = (List<?>) method.invoke(service, args);

        if (result.size() <= listSize) {
            System.out.println("Размер листа совпадает или меньше listSize");
            return result;
        }

        System.out.println("Надо уменьшить лист" + result.subList(0, listSize));
        return result.stream().limit(listSize).collect(Collectors.toList());
    }

    private void writeZip(String saveFileName) {
        String mainPath = System.getProperty("user.dir") + "/cache/" + saveFileName;
        String path = mainPath + ".bin";
        String zipPath = mainPath + ".zip";

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
             FileInputStream fis = new FileInputStream(path)) {

            ZipEntry fleName = new ZipEntry(saveFileName + ".bin");
            zos.putNextEntry(fleName);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zos.write(buffer);
            // закрываем текущую запись для новой записи
            zos.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
