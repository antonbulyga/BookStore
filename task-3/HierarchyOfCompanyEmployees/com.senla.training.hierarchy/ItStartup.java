package task2;

public class ItStartup {
    int maxCountOfEmployee = 3;
    int currentCountOfEmployee = 0;
    public void creator() {
        ItSpecialist[] arrayOfItSpecialists = new ItSpecialist[maxCountOfEmployee];
        int sumWage = 0;

        if(currentCountOfEmployee <= maxCountOfEmployee) {
            ItSpecialist architect = new Architect(3500);
            arrayOfItSpecialists[0] = architect;
            currentCountOfEmployee++;
            ItSpecialist javaDev = new JavaDev(3200);
            arrayOfItSpecialists[1] = javaDev;
            currentCountOfEmployee++;
            ItSpecialist javaScriptDev = new JavaScriptDev(3100);
            arrayOfItSpecialists[2] = javaScriptDev;
            currentCountOfEmployee++;
        }
        else {
            System.out.println("You can't hire more");
        }

        for (int i = 0; i < arrayOfItSpecialists.length; i++) {
             sumWage +=  arrayOfItSpecialists[i].getWage();
        }
        System.out.println("Summary wage is " + sumWage);

    }
}
