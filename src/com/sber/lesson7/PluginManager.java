package com.sber.lesson7;

import plugins.IPlugin;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashMap;

public final class PluginManager {
    private final XLoader xMainLoader;

    public PluginManager(HashMap<String,String> xMainMapClass) {
        xMainLoader = new XLoader(xMainMapClass);
    }

    //Самая безопасная загрузка
    public IPlugin load(final String pluginClassName) throws Exception {
        Class<?> xClass = xMainLoader.loadClass(pluginClassName);
        return (IPlugin) xClass.getConstructor().newInstance();
    }

    //Сможем загрузить класс с тем же именем
    public static IPlugin loadUnique(final HashMap<String,String> xUniqueMapClass, final String pluginClassName) throws Exception {
        XLoader xLoader = new XLoader(xUniqueMapClass);
        Class<?> xClass = xLoader.loadClass(pluginClassName);
        return (IPlugin) xClass.getConstructor().newInstance();
    }

    //Загрузка класса без делегирования предку
    //в таком случае нельзя привести к типу IPlugin, загруженного системным загрузчиком.
    //Плюс надо с плагином хранить скомпилированный IPlugin ( в данном случае в папке main )
    public static Object unsafeLoad(final String pluginName, final String pluginClassName) throws Exception{
        String mainPath = Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/main").toUri().toString();
        String path = Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/" + pluginName).toUri().toString();

        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(path), new URL(mainPath)}, null);

        Class<?> pluginClass = classLoader.loadClass(pluginClassName);

        return pluginClass.getConstructor().newInstance();
    }

    //Вызов метода doUseful у "небезопасного" плагина
    public static void unsafeInvoke(final Object objPlugin) throws Exception{
        Class<?> pluginClass = objPlugin.getClass();

        for (AnnotatedType c: pluginClass.getAnnotatedInterfaces()){
            if(c.toString().equals("plugins.IPlugin")){
                System.out.println("Все ок");
                Method m = pluginClass.getMethod("doUseful");
                m.invoke(objPlugin);
                break;
            } else {
                System.out.println("Класс " + pluginClass.getName() + " не реализует интерфейс IPlugin");
            }
        }

    }

}
