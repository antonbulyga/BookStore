package task2;

public class ItStartup {
    public void creator() {
        ItSpecialist[] arrayOfItSpecialists = new ItSpecialist[3];
        int sumWage = 0;
        ItSpecialist architect = new Architect(3500);
        ItSpecialist javaDev = new JavaDev(3200);
        ItSpecialist javaScriptDev = new JavaScriptDev(3100);

        arrayOfItSpecialists[0] = architect;
        arrayOfItSpecialists[1] = javaDev;
        arrayOfItSpecialists[2] = javaScriptDev;

        for (int i = 0; i < arrayOfItSpecialists.length; i++) {
             sumWage +=  arrayOfItSpecialists[i].getWage();
        }
        System.out.println("Summary wage is " + sumWage);

    }
}
