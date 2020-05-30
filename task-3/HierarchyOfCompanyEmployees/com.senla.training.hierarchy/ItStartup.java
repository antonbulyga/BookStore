package task2;

public class ItStartup {

    private ItSpecialist[] arrayOfItSpecialists = new ItSpecialist[0];

    public ItSpecialist[] create(int maxCountOfEmployee) {

            AdderToArray adderToArray = new AdderToArray();
            ItSpecialist architect = new Architect(3500);
            arrayOfItSpecialists = adderToArray.add(arrayOfItSpecialists, architect);
            if(arrayOfItSpecialists.length <= maxCountOfEmployee) {
                ItSpecialist javaDev = new JavaDev(3200);
                arrayOfItSpecialists = adderToArray.add(arrayOfItSpecialists, javaDev);
            }
            if(arrayOfItSpecialists.length <= maxCountOfEmployee) {
            ItSpecialist javaScriptDev = new JavaScriptDev(3100);
            arrayOfItSpecialists = adderToArray.add(arrayOfItSpecialists, javaScriptDev);
            }
        return arrayOfItSpecialists;
    }
}
