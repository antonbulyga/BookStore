package task1;

import java.util.Random;

public class FinderMaxDigit {
    Random rnd = new Random();
    private int number = rnd.nextInt(899) + 100;
    private int[] mass = new int[3];
    private int max = -1;


    protected int[] divider() {
            int digitFirst = (number / 100) % 10;
            mass[0] = digitFirst;
            int digitSecond = (number / 10) % 10;
            mass[1] = digitSecond;
            int digitThird = number % 10;
            mass[2] = digitThird;

        return mass;
    }
    protected void finder(){
        for (int i = 0; i < mass.length; i++) {
             if(mass[i]>max){
                max = mass[i];
             }
        }
        System.out.println(number);
        System.out.println(max);
    }
}
