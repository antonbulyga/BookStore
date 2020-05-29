package task2;

public class WageCounter {
   private int sumWage = 0;

    public int count(ItSpecialist [] arrayOfItSpecialists){
        for (int i = 0; i < arrayOfItSpecialists.length; i++) {
            sumWage +=  arrayOfItSpecialists[i].getWage();
        }
        System.out.println("Summary wage is " + sumWage);
        return sumWage;
    }
}
