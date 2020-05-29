package task1;

public class FinderMaxDigit {
    private int max = -1;

    protected void find(int[] mass){
        for (int i = 0; i < mass.length; i++) {
            if(mass[i]>max){
                max = mass[i];
            }
        }
        System.out.println(max);

    }
}
