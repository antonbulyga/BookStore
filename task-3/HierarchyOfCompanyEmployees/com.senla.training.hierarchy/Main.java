package task2;


public class Main {

    public static void main(String[] args) {
        ItStartup itSturtup = new ItStartup();
        int maxCountOfEmployee = 2;
        ItSpecialist[] array = itSturtup.create(maxCountOfEmployee);
        WageCounter wageCounter = new WageCounter();
        wageCounter.count(array);
    }
}
