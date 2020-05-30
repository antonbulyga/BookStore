package task2;



public class AdderToArray {
    public ItSpecialist[] add(ItSpecialist[] arrayOfItSpecialists,ItSpecialist itSpecialist){
        ItSpecialist[] copyOfArray = new ItSpecialist[arrayOfItSpecialists.length +1];
        System.arraycopy(arrayOfItSpecialists, 0, copyOfArray, 0, arrayOfItSpecialists.length);
        copyOfArray[copyOfArray.length-1] = itSpecialist;
         return copyOfArray;
    }

}
