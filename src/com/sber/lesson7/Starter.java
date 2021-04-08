package com.sber.lesson7;

import plugins.IPlugin;
import plugins.ITestPM;
import plugins.Weather;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Starter {
    public static void main(String[] args) throws Exception {

        //IPlugin weatherPlugin = new Weather();
        //weatherPlugin.doUseful();

        //Weather wp = new Weather();
        //wp.doUseful();
        //wp.doMethodTest();

        HashMap<String, String> pluginToPathMap = new HashMap<>();
        String pWeather = Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/weather/plugins/Weather.class").toString();
        pluginToPathMap.put("plugins.Weather", pWeather);
        PluginManager pm = new PluginManager(pluginToPathMap);
        pm.load("plugins.Weather").doUseful();

        HashMap<String, String> pluginToPathMapUnique = new HashMap<>();
        String pWeatherUnique = Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/plugins/Weather.class").toString();
        pluginToPathMapUnique.put("plugins.Weather", pWeatherUnique);
        PluginManager.loadUnique(pluginToPathMapUnique, "plugins.Weather").doUseful();

        Object _plugin = PluginManager.unsafeLoad("weather", "plugins.Weather");
        PluginManager.unsafeInvoke(_plugin);
        //Method m = _plugin.getClass().getMethod("doMethodTest");
        //m.invoke(_plugin);
        System.out.println(Arrays.toString(_plugin.getClass().getAnnotatedInterfaces()));


        HashMap<String, String> pluginSecretMap = new HashMap<>();
        //Путь, по которому находится зашифрованный класс
        String pSecret = Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/Secret.class").toString();
        pluginSecretMap.put("com.sber.lesson7.Secret", pSecret);

        CryptoLoader cl = new CryptoLoader(pluginSecretMap);

        //Secret secret = new Secret();
        //secret.saySecret();

        //Берем класс по указанному пути, шифруем и сохраняем по пути pSecret
        cl.encryptClass(Paths.get(System.getProperty("user.dir") + "/pluginRootDirectory/crypto/Secret.class").toString());
        Object sObject = cl.loadClass("com.sber.lesson7.Secret").getConstructor().newInstance();
        Method sMethod = sObject.getClass().getMethod("saySecret");
        sMethod.invoke(sObject);

    }
}
