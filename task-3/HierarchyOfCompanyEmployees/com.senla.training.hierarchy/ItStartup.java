package task2;

public class ItStartup {
    private int maxCountOfEmployee = 3;
    private ItSpecialist[] arrayOfItSpecialists = new ItSpecialist[maxCountOfEmployee];

    public ItSpecialist[] create() {

        if(arrayOfItSpecialists.length <= maxCountOfEmployee) {
            ItSpecialist architect = new Architect(3500);
            arrayOfItSpecialists[0] = architect;

            ItSpecialist javaDev = new JavaDev(3200);
            arrayOfItSpecialists[1] = javaDev;

            ItSpecialist javaScriptDev = new JavaScriptDev(3100);
            arrayOfItSpecialists[2] = javaScriptDev;
        }
        else {
            System.out.println("You can't hire more");
        }
        return arrayOfItSpecialists;




    }
}
