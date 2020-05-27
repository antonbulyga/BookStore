package task1;

public class FinderMaxDigit {
    int [] mass;
    private int max = -1;

    protected void finder(){
        DividerByDigits dividerByDigits = new DividerByDigits();
        mass = dividerByDigits.getMass();

        for (int i = 0; i < mass.length; i++) {
            if(mass[i]>max){
                max = mass[i];
            }
        }
        System.out.println(max);

    }
}
