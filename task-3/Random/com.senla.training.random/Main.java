package task1;


import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RandomGenerator randomGenerator = new RandomGenerator();
        int number = randomGenerator.getRandomNumber();
        DividerByDigits dividerByDigits = new DividerByDigits(number);
        int[] mass = dividerByDigits.divide();
        FinderMaxDigit finderMaxDigit = new FinderMaxDigit();
        finderMaxDigit.find(mass);

    }
}
