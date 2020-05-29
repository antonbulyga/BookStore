package task1;

public class DividerByDigits {
    private int[] mass = new int[3];
    private int number;

    public DividerByDigits(int number) {
        this.number = number;
    }
    protected int[] divide() {

            int digitFirst = (number / 100) % 10;
            mass[0] = digitFirst;
            int digitSecond = (number / 10) % 10;
            mass[1] = digitSecond;
            int digitThird = number % 10;
            mass[2] = digitThird;
        System.out.println(number);

        return mass;
    }

}
