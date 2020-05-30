package task2;

public class ItStartup {

    private ItSpecialist[] arrayOfItSpecialists = new ItSpecialist[0];

    public ItSpecialist[] create(int maxCountOfEmployee) {

            AdderToArray adderToArray = new AdderToArray();
            ItSpecialist architect = new Architect(3500);
            ItSpecialist[] arrayOfItSpecialists1 = adderToArray.add(arrayOfItSpecialists, architect);
            if(arrayOfItSpecialists1.length <= maxCountOfEmployee) {
                ItSpecialist javaDev = new JavaDev(3200);
                ItSpecialist[] arrayOfItSpecialists2 = adderToArray.add(arrayOfItSpecialists1, javaDev);
                arrayOfItSpecialists = arrayOfItSpecialists2;
            }
            if(arrayOfItSpecialists.length <= maxCountOfEmployee) {
            ItSpecialist javaScriptDev = new JavaScriptDev(3100);
            ItSpecialist[] arrayOfItSpecialists3 = adderToArray.add(arrayOfItSpecialists, javaScriptDev);
            arrayOfItSpecialists = arrayOfItSpecialists3;
            }
        return arrayOfItSpecialists;
    }
}
