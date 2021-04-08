package plugins;

public interface ITestPM {
    default void  doMethodTest(){
        System.out.println("Я тестовый интерфейсик ;)");
    }
}
