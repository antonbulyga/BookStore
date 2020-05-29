package task2;


public class Main {
    public static void main(String[] args) {
        ItStartup itSturtup = new ItStartup();
        ItSpecialist[] array = itSturtup.create();
        WageCounter wageCounter = new WageCounter();
        wageCounter.count(array);
    }
}
